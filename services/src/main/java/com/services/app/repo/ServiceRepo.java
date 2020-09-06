package com.services.app.repo;

import com.services.app.domain.ServiceStation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ServiceRepo extends ReactiveCrudRepository<ServiceStation, Long> {
}
