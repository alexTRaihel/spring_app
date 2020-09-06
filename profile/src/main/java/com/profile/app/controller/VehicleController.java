package com.profile.app.controller;

import com.profile.app.domain.Profile;
import com.profile.app.domain.Vehicle;
import com.profile.app.service.UsrService;
import com.profile.app.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin("*")
public class VehicleController {

    private VehicleService vehicleService;
    private UsrService usrService;

    public VehicleController(VehicleService vehicleService, UsrService usrService) {
        this.vehicleService = vehicleService;
        this.usrService = usrService;
    }

    @GetMapping("/{owner_id}")
    public Vehicle getVehicleById(@PathVariable("owner_id") String id){
        return vehicleService.getAllByOwner(Long.parseLong(id)).stream().findFirst().orElse(null);
    }

    @PostMapping("/{owner_id}")
    public ResponseEntity<String> saveVehicleByOwnerId(@PathVariable("owner_id") String id, @RequestBody Vehicle vehicle){

        Profile profile = usrService.getUserById(Long.parseLong(id));
        profile.getVehicles().add(vehicle);
        usrService.saveUser(profile);
        return ResponseEntity.ok("success");
    }
}
