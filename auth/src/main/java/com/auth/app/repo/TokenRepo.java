package com.auth.app.repo;

import com.auth.app.domain.Token;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TokenRepo extends ReactiveCrudRepository<Token, Long> {
}
