package com.station.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Document("plugs")
public class Plug implements Serializable {

    @Id
    private Long id;

    private Long plugShareId;
    private Long locationId;
    private String countryCode;
    private Double score;
    private String address;
    private Integer access;
    private Double latitude;
    private Double longitude;
    private String name;
    private String icon;
    private String url;
    private String iconType;
    private List<Station> stations;
}
