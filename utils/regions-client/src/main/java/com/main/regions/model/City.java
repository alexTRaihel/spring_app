package com.main.regions.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class City implements Serializable {

    private Long cityId;
    private String name;
    private Double latitude;
    private Double longitude;
}
