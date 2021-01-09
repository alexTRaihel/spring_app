package com.serv.app.controller;

import com.main.regions.client.CustomHttpClientExchanger;
import com.main.regions.model.Region;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/regions")
@CrossOrigin("*")
public class RegionController {

    private final CustomHttpClientExchanger regionsLocationProvider = new CustomHttpClientExchanger();

    @GetMapping
    public Flux<Region> getRegions(){
        return regionsLocationProvider.getExchangeFunction(
                "https://oxaspi6smh.execute-api.eu-central-1.amazonaws.com/default/regionsInfo?TableName=regions_info",
                Region[].class).get();
    }
}
