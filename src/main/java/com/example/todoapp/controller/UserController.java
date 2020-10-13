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
    public ResponseEntity<User> getAll(@PathVariable Integer id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @PostMapping
    public String save(@RequestBody User user) {
        System.out.println("user post");
        System.out.println(user);
        return "tot";
        //System.out.println(user);
        //userRepository.save(user);

        // return ResponseEntity(userRepository.findById(id));
        //return ResponseEntity.of(Optional.of(userRepository.findById(id)));
    }
}
