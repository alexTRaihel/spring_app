package com.main.regions.client;

import com.main.regions.handler.RegionsBodyHandler;
import com.main.regions.model.Region;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RegionLocationIdProvider {

    private final String REGIONS_URL_API = "https://oxaspi6smh.execute-api.eu-central-1.amazonaws.com/default/regionsInfo?TableName=regions_info";
//    private final String REGIONS_KEY_API = "https://oxaspi6smh.execute-api.eu-central-1.amazonaws.com/default/regionsInfo?TableName=regions_info";

    HttpClient httpClient = HttpClient.newHttpClient();

    HttpRequest httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(REGIONS_URL_API))
            .timeout(Duration.ofMinutes(1))
            .GET()
            .build();

//    client.sendAsync(request, BodyHandlers.ofString())
//            .thenApply(response -> { System.out.println(response.statusCode());
//        return response; } )
//            .thenApply(HttpResponse::body)
//      .thenAccept(System.out::println);


    public List<Region> getRegions(){

        Region[] regions = null;

        try {
            regions = httpClient
                    .send(httpRequest, new RegionsBodyHandler<>(Region[].class))
                    .body()
                    .get();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return Objects.nonNull(regions) ? List.of(regions) : Collections.emptyList();
    }
}
