package com.profile.app.controller;

import com.profile.app.domain.Profile;
import com.profile.app.service.UsrService;
import com.profile.app.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@CrossOrigin("*")
public class ProfileController {

    private VehicleService vehicleService;
    private UsrService profileService;

    public ProfileController(VehicleService vehicleService, UsrService profileService) {
        this.vehicleService = vehicleService;
        this.profileService = profileService;
    }

    @GetMapping("{id}")
    private Profile getUserDataByID(@PathVariable("id") String id){
        return profileService.getUserById(Long.parseLong(id));
    }

    @PostMapping
    private ResponseEntity<String> saveUserData(@RequestBody Profile profile){
        profileService.saveUser(profile);
        return ResponseEntity.ok().build();
    }
}
