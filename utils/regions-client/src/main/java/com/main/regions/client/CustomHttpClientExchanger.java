package com.main.regions.client;

import com.main.regions.handler.CustomBodyHandler;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.Objects;
import java.util.function.Supplier;

public class CustomHttpClientExchanger {

    private final HttpClient httpClient;

    public CustomHttpClientExchanger() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public <T> Supplier<Flux<T>> getExchangeFunction(String apiURL, Class<T[]> targetClass){

        final HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(apiURL))
                .timeout(Duration.ofMinutes(1))
                .GET()
                .build();

        return () -> {

            T[] regions = null;

            try {
                regions = httpClient
                        .send(httpRequest, new CustomBodyHandler<>(targetClass))
                        .body()
                        .get();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            return Objects.nonNull(regions) ? Flux.fromArray(regions) : Flux.empty();
        };
    }

//    client.sendAsync(request, BodyHandlers.ofString())
//            .thenApply(response -> { System.out.println(response.statusCode());
//        return response; } )
//            .thenApply(HttpResponse::body)
//      .thenAccept(System.out::println);
}
