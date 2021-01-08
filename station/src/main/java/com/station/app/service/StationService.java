package com.station.app.service;

import com.station.app.client.PlugsClient;
import com.station.app.entity.Plug;
import com.station.app.exception.StationAppException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StationService {

    private final PlugsClient plugsClient;

    public StationService(PlugsClient plugsClient) {
        this.plugsClient = plugsClient;
    }

    public Flux<Plug> getStationsByLatitudeAndLongitude(Double latitude, Double longitude, Double spanLng, Double spanLat) {
        if (latitude == null || longitude == null || spanLng == null || spanLat == null) {
            throw new StationAppException("Request params is not valid");
        }
        return this.plugsClient.getPlugsList(latitude, longitude, spanLng, spanLat);
    }

    public Mono<List<Plug>> getNearStations(Double latitude, Double longitude, Integer radius) {
        if (latitude == null || longitude == null || radius == null) {
            throw new StationAppException("Request params is not valid");
        }
        return this.plugsClient.getNearPlugsList(latitude, longitude, radius);
    }

    public Flux<Plug> getNearStationsFlux(Double latitude, Double longitude, Integer radius) {
        if (latitude == null || longitude == null || radius == null) {
            throw new StationAppException("Request params is not valid");
        }
        return this.plugsClient.getNearPlugsListFlux(latitude, longitude, radius);
    }
}
