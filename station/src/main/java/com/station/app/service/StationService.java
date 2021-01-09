package com.station.app.service;

import com.station.app.client.PlugsClient;
import com.station.app.entity.Plug;
import com.station.app.exception.StationAppException;
import com.station.app.repo.PlugsRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StationService {

    private final PlugsClient plugsClient;
    private final PlugsRepository plugsRepository;

    public StationService(PlugsClient plugsClient, PlugsRepository plugsRepository) {
        this.plugsClient = plugsClient;
        this.plugsRepository = plugsRepository;
    }

    public Flux<Plug> getStationsByLatitudeAndLongitudeFromApi(Double latitude, Double longitude, Double spanLng, Double spanLat) {
        if (latitude == null || longitude == null || spanLng == null || spanLat == null) {
            throw new StationAppException("Request params is not valid");
        }
        return this.plugsClient.getPlugsList(latitude, longitude, spanLng, spanLat);
    }

    public Flux<Plug> getStationsFromDbByRegion() {
        return this.plugsRepository.findAll();
    }

    public Flux<Plug> getNearStations(Double latitude, Double longitude, Integer radius) {
        if (latitude == null || longitude == null || radius == null) {
            throw new StationAppException("Request params is not valid");
        }
        return this.plugsClient.getNearPlugsListFlux(latitude, longitude, radius);
    }
}
