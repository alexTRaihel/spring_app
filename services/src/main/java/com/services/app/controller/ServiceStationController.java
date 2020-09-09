package com.services.app.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.services.app.domain.ServiceStation;
import com.services.app.domain.Views;
import com.services.app.service.ServiceStationService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/services")
@CrossOrigin("*")
public class ServiceStationController {

    private final ServiceStationService serviceStationService;

    public ServiceStationController(ServiceStationService serviceStationService) {
        this.serviceStationService = serviceStationService;
    }

    @GetMapping("/{id}")
    @JsonView(Views.FullInfo.class)
    private Mono<ServiceStation> getStationById(@PathVariable("id") String id){
        return serviceStationService.getServiceByServiceId(Long.parseLong(id));
    }

    @GetMapping
    @JsonView(Views.ShortInfo.class)
    private Flux<ServiceStation> getAllServiceStations(){
        return serviceStationService.getAllServiceStations();
    }

    @PostMapping
    private Mono<ServiceStation> createServiceStation(@RequestBody ServiceStation serviceStation){
        return serviceStationService.createServiceStation(serviceStation);
    }
}
