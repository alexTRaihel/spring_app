package com.profile.app.service;

import com.profile.app.domain.Vehicle;
import com.profile.app.error.VehicleOwnerAppException;
import com.profile.app.repo.VehicleRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private VehicleRepo vehicleRepo;

    public VehicleService(VehicleRepo vehicleRepo) {
        this.vehicleRepo = vehicleRepo;
    }

    public Vehicle getVehicleById(Long id){
        return vehicleRepo.findById(id).orElseThrow(()->new VehicleOwnerAppException("exp"));
    }

    public boolean saveVehicle(Vehicle vehicle){
        return vehicleRepo.save(vehicle) != null;
    }

    public List<Vehicle> getAllByOwner(Long id){
        return vehicleRepo.getAllByOwner(id);
    }
}
