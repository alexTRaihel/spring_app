package com.station.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Outlet implements Serializable {

    private Long id;
    private Long plugShareOutletId;
    private Integer connector;
    private Integer power;
    private Integer kilowatts;
}
