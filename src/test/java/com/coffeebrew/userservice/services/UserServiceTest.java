package com.coffeebrew.userservice.services;

import com.coffeebrew.userservice.models.User;
import com.coffeebrew.userservice.repositories.UserRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {
    private EasyRandom random;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService target;

    @BeforeEach
    void setUp() {
        random = new EasyRandom();
    }

    @Test
    public void shouldGetUserById() {
        String id = random.nextObject(String.class);
        User user = random.nextObject(User.class);
        Optional<User> optionalUser = Optional.of(user);

        when(userRepository.findById(id)).thenReturn(optionalUser);

        Optional<User> returnedOptionalUser = target.getUserById(id);

        assertEquals(optionalUser, returnedOptionalUser);
    }

    @Test
    public void shouldSaveUser() {
        User user = random.nextObject(User.class);
        User createdUser = random.nextObject(User.class);

        when(userRepository.save(user)).thenReturn(createdUser);

        User savedUser = target.save(user);

        assertEquals(createdUser, savedUser);
    }
}