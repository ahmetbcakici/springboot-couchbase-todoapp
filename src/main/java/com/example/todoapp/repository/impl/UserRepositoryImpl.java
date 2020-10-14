package com.example.todoapp.repository.impl;

import com.couchbase.client.java.Cluster;
import com.example.todoapp.model.User;
import com.example.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private Cluster couchbaseCluster;

    @Override
    public User findById(Integer id) {
        var userCollection = couchbaseCluster.bucket("user").defaultCollection();
        var userResult = userCollection.get(id.toString());
        return userResult.contentAs(User.class);
    }


    @Override
    public void save(User user) {
        var userCollection = couchbaseCluster.bucket("user").defaultCollection();
        userCollection.insert(String.valueOf(user.getId()), user);

    }
}
