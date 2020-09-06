package com.profile.app.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Table(schema = "profiles_db", name = "profile")
@NoArgsConstructor
@Entity
@ToString( of = {"id", "username"})
@EqualsAndHashCode(of = "id")
public class Profile implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String username;

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    @JsonManagedReference
    private List<Vehicle> vehicles;
}
