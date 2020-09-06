package com.services.app.service;

import com.services.app.domain.ServiceStation;
import com.services.app.repo.ServiceRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ServiceStationService {

    private final ServiceRepo serviceRepo;

    public ServiceStationService(ServiceRepo serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    public Mono<ServiceStation> getServiceByServiceId(Long id){
        return serviceRepo.findById(id);
    }

    public Flux<ServiceStation> getAllServiceStations() {
        return serviceRepo.findAll();
    }

    public Mono<ServiceStation> createServiceStation(ServiceStation serviceStation) {
        return serviceRepo.save(serviceStation);
    }
}
