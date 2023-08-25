package com.ifsju.interfaceweb.sign.repository;

import com.ifsju.interfaceweb.sign.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
