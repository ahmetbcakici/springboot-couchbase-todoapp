package com.example.todoapp.repository;

import com.example.todoapp.model.User;

public interface UserRepository {

    User findById(Integer id);
}
