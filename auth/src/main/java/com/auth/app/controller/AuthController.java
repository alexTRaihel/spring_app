package com.auth.app.controller;

import com.auth.app.domain.UserCredentials;
import com.auth.app.service.UserService;
import com.auth.app.util.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
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
    public Mono<ResponseEntity> login(@RequestBody UserCredentials credentials) {
        return Mono.from(
                userService.findByUserName(credentials.getUsername())
                        .map(userFromDB ->
                                Objects.equals(credentials.getPassword(), userFromDB.getPassword())
                                        ? ResponseEntity.ok(jwtUtil.generateToken(userFromDB)) : UNAUTHORIZED
                        )
                        .defaultIfEmpty(UNAUTHORIZED)
        );
    }
}
