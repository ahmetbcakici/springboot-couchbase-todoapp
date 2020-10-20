package com.example.todoapp.repository;

import com.couchbase.client.java.kv.MutationResult;
import com.example.todoapp.model.User;

public interface UserRepository {
    User findById(Integer id);

    void save(User user);

    void deleteUserById(Integer id);

    MutationResult updateUserById(User user);
}
