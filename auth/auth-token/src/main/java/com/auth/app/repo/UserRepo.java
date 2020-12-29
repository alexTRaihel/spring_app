package com.auth.app.repo;

import com.auth.app.domain.UserCredentials;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepo extends ReactiveCrudRepository<UserCredentials, Long> {
    Mono<UserCredentials> findByUsername(String username);
}
