package com.station.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Station implements Serializable {

    private Long id;
    private List<Outlet> outlets;
}
