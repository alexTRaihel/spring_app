package com.station.app;

import com.station.app.client.PlugsClient;
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
}
