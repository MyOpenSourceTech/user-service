package com.coffeebrew.userservice.controllers;

import com.coffeebrew.userservice.models.User;
import com.coffeebrew.userservice.services.UserService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserControllerTest {
    EasyRandom random;
    @Mock
    UserService userService;

    @InjectMocks
    UserController target;

    @BeforeEach
    void setUp() {
        random = new EasyRandom();
    }

    @Test
    public void shouldCreateNewUser() {
        User user = random.nextObject(User.class);
        when(userService.save(user)).thenReturn(user);

        ResponseEntity<User> responseEntity = target.save(user);

        assertEquals(user, responseEntity.getBody());
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void shouldGetUserById() {
        String id = random.nextObject(String.class);
        User user = random.nextObject(User.class);
        when(userService.getUserById(id)).thenReturn(Optional.of(user));

        ResponseEntity<User> responseEntity = target.getById(id);

        assertEquals(user, responseEntity.getBody());
    }

    @Test
    public void shouldReturnNotFoundStatusWhileUserIsMissing() {
        String id = random.nextObject(String.class);
        when(userService.getUserById(id)).thenReturn(Optional.empty());

        ResponseEntity<User> responseEntity = target.getById(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void shouldReturnUserByName() {
        String name = random.nextObject(String.class);
        User user = random.nextObject(User.class);

        when(userService.getByName(name)).thenReturn(Optional.of(user));

        ResponseEntity<User> responseEntity = target.getByName(name);

        assertEquals(user, responseEntity.getBody());
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void shouldReturnNotFoundStatusWhileUserNameIsMissing() {
        String name = random.nextObject(String.class);
        when(userService.getByName(name)).thenReturn(Optional.empty());

        ResponseEntity<User> responseEntity = target.getByName(name);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}