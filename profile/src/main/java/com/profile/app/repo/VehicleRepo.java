package com.profile.app.repo;

import com.profile.app.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    List<Vehicle> getAllByOwner(Long id);
}
