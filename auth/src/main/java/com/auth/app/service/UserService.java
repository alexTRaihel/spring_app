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
        userCredentials.getId();
        return userRepo.save(userCredentials);
    }

    public Mono<UserCredentials> findByUserName(String username){
        return userRepo.findByUsername(username);
    }
}
