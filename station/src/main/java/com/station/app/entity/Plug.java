package com.station.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Plug implements Serializable {

    private Long id;
    private Double score;
    private String address;
    private Integer access;
    private Double latitude;
    private Double longitude;
    private String name;
    private String icon;
    private String url;
    private String icon_type;

    private List<Station> stations;
}
