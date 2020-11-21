package com.auth.utils.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenAuthenticationManager {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expirationTime;

    public UsernamePasswordAuthenticationToken processAuthentication(String token) throws AuthenticationException {

        Claims claims;

        String key = Base64.getEncoder().encodeToString(secret.getBytes());

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (Exception ex) {
            throw new AuthenticationServiceException("Token corrupted");
        }

        if (claims.getExpiration() != null && claims.getExpiration().after(new Date())) {
            return buildAuthenticationToken(claims);
        } else {
            throw new AuthenticationServiceException("Token expired date error");
        }
    }

    private UsernamePasswordAuthenticationToken buildAuthenticationToken(Claims claims) {

        String username = claims.getSubject();

        List<String> roles = claims.get("ROLE", List.class);

        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(
                username,
                null,
                authorities
        );
    }
}
