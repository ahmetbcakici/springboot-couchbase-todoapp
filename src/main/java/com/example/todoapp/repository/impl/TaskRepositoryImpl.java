package com.example.todoapp.repository.impl;

import com.couchbase.client.java.Cluster;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.User;
import com.example.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @Autowired
    private Cluster couchbaseCluster;

    @Override
    public List<Task> findUserTasks() {
        System.out.println("also here!");
        var userCollection = couchbaseCluster.bucket("user").defaultCollection();
        var userResult = userCollection.get("1905");
        System.out.println(userResult.contentAs(User.class));
        System.out.println(userResult);
        return null;
        //return userResult.contentAs(Task.class);
    }
}
