package com.services.app.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Table("service_station")
public class ServiceStation implements Serializable {

    @Id
    private Long id;
    private String name;
    private String address;
    private Boolean access;
    private Double latitude;
    private Double longitude;
    //private String icon;
    //private String url;
    private Integer city;
}
