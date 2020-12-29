package com.profile.app.controller;

import com.profile.app.domain.Profile;
import com.profile.app.service.UsrService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@CrossOrigin("*")
public class ProfileController {

    private UsrService profileService;

    public ProfileController(UsrService profileService) {
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
