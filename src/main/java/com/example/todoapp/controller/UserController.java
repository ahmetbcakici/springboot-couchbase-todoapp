package com.example.todoapp.controller;

import com.example.todoapp.model.User;
import com.example.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @PostMapping
    public User saveNewUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @PutMapping()
    public User updateUser(@RequestBody User user) {
        userRepository.updateUserById(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public Boolean deleteUserById(@PathVariable Integer id) {
        userRepository.deleteUserById(id);
        return true;
    }
}
