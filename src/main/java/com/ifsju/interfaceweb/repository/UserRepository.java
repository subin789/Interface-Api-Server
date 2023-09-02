package com.ifsju.interfaceweb.repository;

import com.ifsju.interfaceweb.entity.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Id> {
    User findById(Long id);
    Optional<User> findByEmail(String email);
}
