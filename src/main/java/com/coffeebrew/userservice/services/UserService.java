package com.coffeebrew.userservice.services;

import com.coffeebrew.userservice.models.User;
import com.coffeebrew.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getByName(String name) {
        return userRepository.findByName(name);
    }
}
