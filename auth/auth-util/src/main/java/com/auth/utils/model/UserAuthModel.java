package com.auth.utils.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserAuthModel implements Serializable {

    private Long id;
    private String username;
    private String password;

    public UserAuthModel(String username, String password){
        this.username = username;
        this.password = password;
    }
}
