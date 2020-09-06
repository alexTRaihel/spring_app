package com.profile.app.repo;

import com.profile.app.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrRepo extends JpaRepository<Profile, Long> {
    Profile findByUsername(String username);
}
