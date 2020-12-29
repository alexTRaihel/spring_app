package com.auth.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("user_credentials")
public class UserCredentials {

    @Id
    private Long id;
    private String username;
    private String password;

    public UserCredentials(String username, String password){
        this.username = username;
        this.password = password;
    }
}
