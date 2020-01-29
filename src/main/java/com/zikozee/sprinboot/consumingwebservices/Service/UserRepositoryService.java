package com.zikozee.sprinboot.consumingwebservices.Service;

import com.zikozee.sprinboot.consumingwebservices.DatabaseEntity.Users;

import java.util.List;

public interface UserRepositoryService {
    List<Users> findAll();
    Users findByUsername(String userName);
}
