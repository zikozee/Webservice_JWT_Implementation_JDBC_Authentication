package com.zikozee.sprinboot.consumingwebservices.Service;

import com.zikozee.sprinboot.consumingwebservices.DatabaseEntity.Users;
import com.zikozee.sprinboot.consumingwebservices.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepositoryServiceImpl implements UserRepositoryService {
    private UserRepository userRepository;

    public UserRepositoryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Users findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
}
