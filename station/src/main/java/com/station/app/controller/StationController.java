package com.station.app.controller;

import com.station.app.entity.Plug;
import com.station.app.exception.StationAppException;
import com.station.app.service.StationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/stations")
@CrossOrigin("*")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<Plug>> getStations(@RequestParam("latitude") String latitude,
                                   @RequestParam("longitude") String longitude,
                                   @RequestParam("spanLng") String spanLng,
                                   @RequestParam("spanLat") String spanLat) {

        Double latitudeDouble;
        Double longitudeDouble;
        Double spanLngDouble;
        Double spanLatDouble;

        try {
            latitudeDouble = Double.parseDouble(latitude);
            longitudeDouble = Double.parseDouble(longitude);
            spanLngDouble = Double.parseDouble(spanLng);
            spanLatDouble = Double.parseDouble(spanLat);
        } catch (ClassCastException ex){
            throw new StationAppException("Request params is not valid exception");
        }

        return stationService.getStationsByLatitudeAndLongitude(latitudeDouble, longitudeDouble, spanLngDouble, spanLatDouble);
    }

    @GetMapping(path = "/near", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<Plug>> getNearStations(@RequestParam("latitude") Double latitude,
                                        @RequestParam("longitude") Double longitude,
                                        @RequestParam("radius") Integer radius) {
        return stationService.getNearStations(latitude, longitude, radius);
    }
}
