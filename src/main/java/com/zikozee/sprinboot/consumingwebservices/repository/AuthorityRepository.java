package com.zikozee.sprinboot.consumingwebservices.repository;

import com.zikozee.sprinboot.consumingwebservices.DatabaseEntity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByUsername(String username);
}
