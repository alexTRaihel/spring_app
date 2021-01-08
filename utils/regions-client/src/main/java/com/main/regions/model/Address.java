package com.main.regions.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class Address implements Serializable {

    private String country_code;
    private String formatted;
    private String country;
    private String province;
    private String area;
    private String locality;
    private String street;
    private String house;

    private Long cityId;
    private Long regionId;
}
