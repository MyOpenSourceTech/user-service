package com.coffeebrew.userservice.controllers;

import com.coffeebrew.userservice.models.User;
import com.coffeebrew.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userOptional.get(), HttpStatus.CREATED);
        }
    }

    @GetMapping
    public ResponseEntity<User> getByName(@RequestParam("name") String name) {
        Optional<User> userOptional = userService.getByName(name);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
        }
    }
}
