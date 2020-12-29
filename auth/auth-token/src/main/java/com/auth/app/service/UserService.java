package com.auth.app.service;

import com.auth.app.domain.UserCredentials;
import com.auth.app.repo.UserRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Mono<UserCredentials> saveUser(UserCredentials userCredentials){
//        checkUser(userCredentials)
        return userRepo.save(userCredentials);
    }

    public Mono<UserCredentials> checkUser(UserCredentials userCredential){
        return userRepo.findByUsername(userCredential.getUsername());
    }

    public Mono<UserCredentials> findByUserName(String username){
        return userRepo.findByUsername(username);
    }
}
