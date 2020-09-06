package com.profile.app.service;

import com.profile.app.domain.Profile;
import com.profile.app.repo.UsrRepo;
import org.springframework.stereotype.Service;

@Service
public class UsrService {

    private UsrRepo usrRepo;

    public UsrService(UsrRepo usrRepo) {
        this.usrRepo = usrRepo;
    }

    public Profile getUserByUsername(String username){
        return usrRepo.findByUsername(username);
    }

    public Profile saveUser(Profile profile){
        return usrRepo.save(profile);
    }

    public Profile updateUser(Profile profile){
        return usrRepo.save(profile);
    }

    public Profile getUserById(Long id){
        return usrRepo.findById(id).orElse(null);
    }
}
