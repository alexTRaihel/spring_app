package com.profile.app.error;

import lombok.Getter;

@Getter
public class VehicleOwnerAppException extends RuntimeException {
    private final String message;

    public VehicleOwnerAppException(String message) {
        super(message);
        this.message = message;
    }
}
