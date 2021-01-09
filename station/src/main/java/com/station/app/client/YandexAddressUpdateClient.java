package com.station.app.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.station.app.entity.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
public class YandexAddressUpdateClient {

    private final WebClient webClient;
    private final String apiKey;
    private final ObjectMapper objectMapper;

//    https://geocode-maps.yandex.ru/1.x/?apikey=8023ff05-f343-4b00-bb55-b6996e04ecf1
//    &geocode=30.014725,52.929239&format=json&lang=ru_RU&kind=street

    public YandexAddressUpdateClient(WebClient.Builder webClientBuilder,
                                     String url,
                                     String apikey,
                                     ObjectMapper objectMapper) {
        this.webClient = webClientBuilder
                .baseUrl(url)
                .build();
        this.apiKey = apikey;
        this.objectMapper = objectMapper;
    }

    public Mono<Address> getInfoByCoordinates(Double latitude,
                                              Double longitude,
                                              String lang,
                                              Integer results,
                                              String kind){
        return this.webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("apikey", apiKey)
                        .queryParam("geocode",
                                longitude +
                                        "," +
                                        latitude)
                        .queryParam("lang", lang)
                        .queryParam("kind", kind)
                        .queryParam("results", results)
                        .queryParam("format", "json")
                        .build())
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(String.class))
                .flatMap(this::extractAddress);
    }

    public Mono<Address> getInfoByAdress(String geocode,
                                         String lang,
                                         Integer results,
                                         String kind) {
        return this.webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("apikey", apiKey)
                        .queryParam("geocode", geocode)
                        .queryParam("lang", lang)
                        .queryParam("kind", kind)
                        .queryParam("results", results)
                        .queryParam("format", "json")
                        .build())
                .exchange().flatMap(clientResponse -> clientResponse.bodyToMono(Address.class));

    }

    private Mono<Address> extractAddress(String string){

        Address address = new Address();

        try {
            JsonNode nodeRoot = objectMapper.readTree(string);
            JsonNode featureMember = nodeRoot
                    .get("response")
                    .get("GeoObjectCollection")
                    .get("featureMember")
                    .get(0);
            if (Objects.nonNull(featureMember)) {

                JsonNode nodeAddress = featureMember
                        .get("GeoObject")
                        .get("metaDataProperty")
                        .get("GeocoderMetaData")
                        .get("Address");


                address.setCountryCode(nodeAddress.get("country_code").textValue());
                address.setFormatted(nodeAddress.get("formatted").textValue());
            } else {
                log.error(nodeRoot.asText());
            }

        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException ex){
            log.error(ex.getMessage());
        }

        return Mono.just(address);
    }


//    ExchangeStrategies strategies = ExchangeStrategies
//            .builder()
//            .codecs(clientDefaultCodecsConfigurer -> {
//                clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(new ObjectMapper(), MediaType.APPLICATION_JSON));
//                clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(new ObjectMapper(), MediaType.APPLICATION_JSON));
//
//            }).build();
//
//    WebClient webClient = WebClient.builder().exchangeStrategies(strategies).build();
}
