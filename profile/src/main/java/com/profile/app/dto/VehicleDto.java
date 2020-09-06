package com.profile.app.dto;

import com.profile.app.domain.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleDto {

    private String model;
    private Profile owner;
}
