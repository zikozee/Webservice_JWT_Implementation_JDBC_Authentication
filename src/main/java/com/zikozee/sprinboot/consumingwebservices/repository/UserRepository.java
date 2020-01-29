package com.zikozee.sprinboot.consumingwebservices.repository;

import com.zikozee.sprinboot.consumingwebservices.DatabaseEntity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);
}