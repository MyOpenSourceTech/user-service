package com.coffeebrew.userservice.repositories;

import com.coffeebrew.userservice.models.User;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    EasyRandom random;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        random = new EasyRandom();
    }

    @Test
    public void shouldSaveAndFetchUser() {
        User user = random.nextObject(User.class);
        User savedUser = userRepository.save(user);
        Optional<User> fetchedUser = userRepository.findById(savedUser.getId());
        assertEquals(savedUser.getId(), fetchedUser.get().getId());
        assertEquals(savedUser.getName(), fetchedUser.get().getName());
    }

}