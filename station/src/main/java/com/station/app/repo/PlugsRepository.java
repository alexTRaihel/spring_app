package com.station.app.repo;

import com.station.app.entity.Plug;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PlugsRepository extends ReactiveMongoRepository<Plug, Long> {

    @Override
    <S extends Plug> Mono<S> save(S entity);
}
