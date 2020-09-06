package com.services.app.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Table("cities")
public class City implements Serializable {

    @Id
    private Long id;
    private String cityId;
    private String name;
    private Double lat;
    private Double lon;

    private Region region;
}
