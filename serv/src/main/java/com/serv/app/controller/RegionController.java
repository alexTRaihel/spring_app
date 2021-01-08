package com.serv.app.controller;

import com.main.regions.client.RegionLocationIdProvider;
import com.main.regions.model.Region;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/regions")
public class RegionController {

    private final RegionLocationIdProvider regionsLocationProvider = new RegionLocationIdProvider();

    @GetMapping
    public Flux<Region> getRegions(){
        return Mono.just(
                regionsLocationProvider.getRegions()
        ).flatMapMany(Flux::fromIterable);
    }
}
