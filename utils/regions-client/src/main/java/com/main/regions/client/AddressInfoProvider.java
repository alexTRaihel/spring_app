package com.main.regions.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.regions.model.Address;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class AddressInfoProvider {

    private final ObjectMapper objectMapper;

    public AddressInfoProvider() {
        this.objectMapper = new ObjectMapper();
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
//                log.(nodeRoot.asText());
            }

        } catch (JsonProcessingException e) {
//            log.error(e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException ex){
//            log.error(ex.getMessage());
        }

        return Mono.just(address);
    }
}
