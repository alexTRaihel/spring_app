package com.auth.app.service;

import com.auth.app.repo.TokenRepo;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenRepo tokenRepo;

    public TokenService(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }


}
