package com.example.todoapp.repository.impl;

import com.couchbase.client.java.Collection;
import static com.couchbase.client.java.kv.LookupInSpec.get;
import com.couchbase.client.java.kv.LookupInResult;
import com.example.todoapp.model.Task;
import com.example.todoapp.model.User;
import com.example.todoapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final Collection userCollection;

    @Override
    public List<Task> findUserTasks() {
        System.out.println("also here!");
        var userResult = userCollection.get("1905");
        System.out.println(userResult.contentAs(User.class));
        System.out.println(userResult.contentAs(Task.class));
        System.out.println(userResult);
        //        return userResult.contentAs(User.class);
        return null;
    }

    @Override
    public Boolean deleteTaskById(int id) {
        LookupInResult result = userCollection.lookupIn(
                "1905",
                Collections.singletonList(get("tasks"))
        );
        System.out.println(result);
        System.out.println(result.contentAsArray(0));

        //String str = result.contentAs(0, String.class);
        //System.out.println("getFunc: Altitude = " + str);
        return null;
    }
}
