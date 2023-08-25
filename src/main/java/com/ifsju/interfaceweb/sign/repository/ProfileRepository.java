package com.ifsju.interfaceweb.sign.repository;

import com.ifsju.interfaceweb.sign.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
