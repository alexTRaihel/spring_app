package com.serv.app.repo;

import com.serv.app.domain.ServiceStation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ServiceRepo extends ReactiveCrudRepository<ServiceStation, Long> {
    Mono<Void> deleteById(Long id);
}
