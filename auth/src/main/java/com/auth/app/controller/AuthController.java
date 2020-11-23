package com.auth.app.controller;

import com.auth.app.domain.UserCredentials;
import com.auth.app.service.UserService;
import com.auth.app.util.JWTUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
@CrossOrigin(allowCredentials = "true", origins = {"http://localhost:8000"})
public class AuthController {

    private static final ResponseEntity<Object> UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    private final UserService userService;
    private final JWTUtil jwtUtil;

    public AuthController(UserService userService, JWTUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public Mono<UserCredentials> signUp(@RequestBody UserCredentials userCredentials) {
        return userService.saveUser(userCredentials);
    }

    @PostMapping("/login")
    public Mono<ResponseEntity> login(ServerHttpRequest serverHttpRequest, @RequestBody UserCredentials credentials,
                                      ServerWebExchange exchange,
                                      ServerHttpResponse serverHttpResponse) {
        return Mono.from(
                userService.findByUserName(credentials.getUsername())
                        .map(userFromDB ->
                                Objects.equals(credentials.getPassword(), userFromDB.getPassword())
                                        ? ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, getAuthCookies(userFromDB))
                                        .body(jwtUtil.generateToken(userFromDB)): UNAUTHORIZED
                        )
                        .defaultIfEmpty(UNAUTHORIZED)
        );
    }

    private String getAuthCookies(UserCredentials userFromDB){
        String token = jwtUtil.generateToken(userFromDB);
        return ResponseCookie.from("auth_token", token).build().toString();
    }
}
