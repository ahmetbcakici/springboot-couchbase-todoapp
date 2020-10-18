package com.example.todoapp.repository.impl;

import com.couchbase.client.java.Collection;
import com.example.todoapp.model.User;
import com.example.todoapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final Collection userCollection;

    @Override
    public User findById(Integer id) {
        var userResult = userCollection.get(id.toString());
        return userResult.contentAs(User.class);
    }

    @Override
    public void save(User user) {
        userCollection.insert(String.valueOf(user.getId()), user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userCollection.remove(id.toString());
    }
}
