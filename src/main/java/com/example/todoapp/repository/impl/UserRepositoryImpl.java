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
        var userBucket = couchbaseCluster.bucket("user");
        var userResult = userBucket.defaultCollection().get(id.toString());
        return userResult.contentAs(User.class);
        /*
        User user = new User();
        user.setId(1);
        user.setName("Ahmet");*/

    }
}
