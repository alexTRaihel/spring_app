package com.services.app.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Table("region")
public class Region implements Serializable {

    @Id
    private Long id;
    private String name;
    private List<City> cities;
}
