package com.auth.utils.config;

import com.auth.utils.jwt.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpCookie;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Objects;

@Configuration
public class SecurityConfiguration {

    @Bean
    public AuthenticationWebFilter authenticationWebFilter(ReactiveAuthenticationManager jwtAuthenticationManager,
                                                           ServerAuthenticationConverter jwtAuthenticationConverter){
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(jwtAuthenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(jwtAuthenticationConverter);
        return authenticationWebFilter;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, AuthenticationWebFilter authenticationWebFilter){
        return http.cors().and()
                .authorizeExchange()
                .pathMatchers("/auth/**")
                    .permitAll()
                .pathMatchers("/validate")
                    .authenticated()
                .and()
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .httpBasic()
                .disable()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .logout()
                .disable()
                .build();
    }

    @Bean
    public ServerAuthenticationConverter jwtAuthenticationConverter() {
        return exchange ->
                Mono.justOrEmpty(exchange)
                    .map(ex -> ex.getRequest().getCookies())
                    .filter(valueMap -> valueMap.size()>0 && Objects.nonNull(valueMap.getFirst("X-Auth")))
                    .map(cookie->cookie.getFirst("X-Auth"))
                    .map(HttpCookie::getValue)
                    .map(token -> new UsernamePasswordAuthenticationToken(token, token));
    }

    @Bean
    public JWTUtil jwtUtil(){
        return new JWTUtil();
    }

    @Bean
    public ReactiveAuthenticationManager jwtAuthenticationManager(JWTUtil jwtUtil) {
        return authentication ->
                Mono.justOrEmpty(authentication)
                    .map(auth -> jwtUtil.validateJwt((String) auth.getCredentials()))
                    .map(jws ->
                            new UsernamePasswordAuthenticationToken(
                                    jws.getBody().getSubject(),
                                    authentication.getCredentials(),
                                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                            )
                    );
    }
}

