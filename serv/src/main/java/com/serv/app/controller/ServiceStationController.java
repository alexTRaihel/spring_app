package com.serv.app.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.serv.app.domain.ServiceStation;
import com.serv.app.domain.Views;
import com.serv.app.service.ServiceStationService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/services")
@CrossOrigin("http://localhost:8000")
public class ServiceStationController {

    private final ServiceStationService serviceStationService;

    public ServiceStationController(ServiceStationService serviceStationService) {
        this.serviceStationService = serviceStationService;
    }

    @GetMapping("/{id}")
    @JsonView(Views.FullInfo.class)
    private Mono<ServiceStation> getStationById(@PathVariable("id") String id) {
        return serviceStationService.getServiceByServiceId(Long.parseLong(id));
    }

    @GetMapping
    @JsonView(Views.ShortInfo.class)
    private Flux<ServiceStation> getAllServiceStations() {
        return serviceStationService.getAllServiceStations();
    }

    @PostMapping
    private Mono<ServiceStation> createServiceStation(@RequestBody ServiceStation serviceStation) {
        return serviceStationService.createServiceStation(serviceStation);
    }

    @PostMapping("/update")
    private Mono<ServiceStation> updateServiceStation(@RequestBody ServiceStation serviceStation) {
        return serviceStationService.updateServiceStation(serviceStation);
    }

    @DeleteMapping("/remove/{id}")
    private Mono<Void> deleteServiceStation(@PathVariable("id") String id) {
        return serviceStationService.deleteServiceStationById(id);
    }
}
