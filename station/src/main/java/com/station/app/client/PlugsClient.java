package com.station.app.client;

import com.station.app.entity.Plug;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class PlugsClient {

    @Value("${plugs.minimal}")
    private String minimal;

    @Value("${plugs.count}")
    private String count;

    private WebClient webClient;

    public PlugsClient(WebClient.Builder webClientBuilder, String url, String authorization) {
        this.webClient = webClientBuilder
                .baseUrl(url)
                .defaultHeader("authorization", "Basic " + authorization)
                .build();
    }

    public Flux<Plug> getPlugsList(Double latitude, Double longitude, Double spanLng, Double spanLat){
        return this.webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/region")
                        .queryParam("minimal", minimal)
                        .queryParam("count", count)
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("spanLng", spanLng)
                        .queryParam("spanLat", spanLat)
                        .queryParam("access", 1)
                        .build())
                .exchange()
                .flux()
                .flatMap(clientResponse -> clientResponse.bodyToFlux(Plug.class));
    }

    public Mono<List<Plug>> getNearPlugsList(Double latitude, Double longitude, Integer radius){
        return this.webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/nearby")
                        .queryParam("minimal", minimal)
                        .queryParam("count", count)
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("radius", radius)
                        .build())
                .exchange().flatMap(clientResponse -> clientResponse.bodyToMono(new ParameterizedTypeReference<List<Plug>>() {
                }));
    }

    public Flux<Plug> getNearPlugsListFlux(Double latitude, Double longitude, Integer radius){
        return this.webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/nearby")
                        .queryParam("minimal", minimal)
                        .queryParam("count", count)
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("radius", radius)
                        .build())
                .exchange()
                .flux()
                .flatMap(clientResponse -> clientResponse.bodyToFlux(Plug.class));
    }
}
