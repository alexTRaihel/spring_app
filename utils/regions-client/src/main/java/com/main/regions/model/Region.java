package com.main.regions.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Region implements Serializable {

    private Long regionId;
    private String name;
    private Double latitude;
    private Double longitude;

    private List<City> cities;
}
