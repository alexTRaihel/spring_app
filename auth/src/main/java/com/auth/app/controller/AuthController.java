package com.auth.app.controller;

import com.auth.app.domain.UserCredentials;
import com.auth.app.service.AuthService;
import com.auth.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PutMapping("/auth/signup")
    public Mono<UserCredentials> signUp(@RequestBody UserCredentials userCredentials) {
        return userService.saveUser(userCredentials);
    }

    @PostMapping("/auth/login")
    public Mono<ResponseEntity> login(ServerHttpRequest serverHttpRequest, @RequestBody UserCredentials credentials) {
        return authService.doLogin(credentials, serverHttpRequest);
    }

    @GetMapping("/validate")
    public Mono<ResponseEntity> login(ServerHttpRequest serverHttpRequest) {
        return authService.validate(serverHttpRequest);
    }
}