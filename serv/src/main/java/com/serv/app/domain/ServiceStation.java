package com.serv.app.domain;

import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.ShortInfo.class)
    private Integer regionId;
    @JsonView(Views.ShortInfo.class)
    private Integer cityId;

    @JsonView(Views.FullInfo.class)
    private Double score;

    @JsonView(Views.ShortInfo.class)
    private String name;
    @JsonView(Views.ShortInfo.class)
    private String address;
    @JsonView(Views.ShortInfo.class)
    private Boolean access;
    @JsonView(Views.ShortInfo.class)
    private Double latitude;
    @JsonView(Views.ShortInfo.class)
    private Double longitude;
    @JsonView(Views.FullInfo.class)
    private String icon;
    @JsonView(Views.FullInfo.class)
    private String description;
    @JsonView(Views.ShortInfo.class)
    private String url;
    @JsonView(Views.ShortInfo.class)
    private Boolean evacuation;
}
