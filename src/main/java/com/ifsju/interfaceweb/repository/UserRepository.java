package com.ifsju.interfaceweb.repository;

import com.ifsju.interfaceweb.entity.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Id> {
    User findById(Long id);
}
