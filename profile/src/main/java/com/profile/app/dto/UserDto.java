package com.profile.app.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.profile.app.domain.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String username;

    @JsonRawValue
    private List<Vehicle> vehicles;
}
