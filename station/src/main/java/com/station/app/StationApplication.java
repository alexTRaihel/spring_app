package com.station.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.station.app.client.PlugsClient;
import com.station.app.client.YandexAddressUpdateClient;
import com.station.app.repo.PlugsRepository;
import com.station.app.schedule.PlugsDataUpdateJob;
import com.station.app.service.StationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class StationApplication {
    public static void main(String[] args) {
        SpringApplication.run(StationApplication.class, args);
    }

    @Bean
    public PlugsClient getPlugsClient(WebClient.Builder webClientBuilder,
                                      @Value("${plugs.url}") String url,
                                      @Value("${plugs.authorization}") String authorization){
        return new PlugsClient(webClientBuilder, url, authorization);
    }

    @Bean
    public YandexAddressUpdateClient getAddressUpdateClient(WebClient.Builder webClientBuilder,
                                                            @Value("${geocode.url}") String url,
                                                            @Value("${geocode.apiKey}") String apiKey){
        return new YandexAddressUpdateClient(webClientBuilder, url, apiKey, new ObjectMapper());
    }

    @Bean
    public PlugsDataUpdateJob updatePlugsData(StationService stationService,
                                              PlugsRepository plugsRepository,
                                              YandexAddressUpdateClient yandexAddressUpdateClient){
        return new PlugsDataUpdateJob(stationService, plugsRepository, yandexAddressUpdateClient);
    }
}
