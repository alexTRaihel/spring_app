package com.auth.app.service;

import com.auth.app.domain.UserCredentials;
import com.auth.app.util.JWTUtil;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class AuthService {

    private static final ResponseEntity<Object> UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    private final UserService userService;
    private final JWTUtil jwtUtil;

    public AuthService(UserService userService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public Mono<ResponseEntity> doLogin(UserCredentials credentials, ServerHttpRequest serverHttpRequest){
        return Mono.from(userService.findByUserName(credentials.getUsername())
                .map(userFromDB ->
                        Objects.equals(credentials.getPassword(), userFromDB.getPassword())
                                ? getResponse(userFromDB, serverHttpRequest) : UNAUTHORIZED
                )
                .defaultIfEmpty(UNAUTHORIZED)
        );
    }

    public Mono<ResponseEntity> validate(ServerHttpRequest serverHttpRequest){
        System.out.println(serverHttpRequest.getCookies().toSingleValueMap());
        return Mono.justOrEmpty(ResponseEntity.ok("Ok"));
    }

    private ResponseEntity getResponse(UserCredentials userFromDB, ServerHttpRequest serverHttpRequest){

        String jwt = jwtUtil.generateJwt(userFromDB);

        ResponseCookie authCookie = ResponseCookie.fromClientResponse("X-Auth", jwt)
                .maxAge(3600)
                .httpOnly(true)
                .path("/")
                .secure(false)
                .build();

        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, authCookie.toString())
                .build();
    }
}
