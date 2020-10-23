package com.example.todoapp.repository.impl;

import com.couchbase.client.core.error.subdoc.PathExistsException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.LookupInResult;
import com.example.todoapp.model.enums.Status;
import com.example.todoapp.model.Task;
import com.example.todoapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.todoapp.model.enums.Status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.couchbase.client.java.kv.LookupInSpec.get;
import static com.couchbase.client.java.kv.MutateInSpec.insert;
import static com.couchbase.client.java.kv.LookupInSpec.exists;
import static com.couchbase.client.java.kv.LookupInSpec.get;
import static com.couchbase.client.java.kv.MutateInOptions.mutateInOptions;
import static com.couchbase.client.java.kv.MutateInSpec.arrayAddUnique;
import static com.couchbase.client.java.kv.MutateInSpec.arrayAppend;
import static com.couchbase.client.java.kv.MutateInSpec.arrayInsert;
import static com.couchbase.client.java.kv.MutateInSpec.arrayPrepend;
import static com.couchbase.client.java.kv.MutateInSpec.decrement;
import static com.couchbase.client.java.kv.MutateInSpec.increment;
import static com.couchbase.client.java.kv.MutateInSpec.insert;
import static com.couchbase.client.java.kv.MutateInSpec.remove;
import static com.couchbase.client.java.kv.MutateInSpec.upsert;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final Collection userCollection;

    @Autowired
    private Cluster couchbaseCluster;

    private String userId = "1905";

    // @TODO: not working as i want
    @Override
    public List<Task> findUserTasks() {

        /*LookupInResult result = userCollection.lookupIn(
                userId,
                Collections.singletonList(get("tasks"))
        );
        var x = result.contentAsArray(0);*/

        var tasks = couchbaseCluster.query
                ("SELECT tasks FROM `user` where id=" + userId + "")
                .rowsAs(Task.class);


        return tasks;
    }

    @Override
    public Task findTaskById(int id) {
        return null;
    }

    @Override
    public Task addNewTask(Task task) {
        try {
            userCollection.mutateIn(userId, Collections.singletonList(
                    arrayAppend("tasks", Collections.singletonList(task))
            ));
        } catch (PathExistsException err) {
            System.out.println("insertFunc: exception caught, path already exists");
        }

        return null;
    }

    //@Override
    //public Task findTaskById(int id) {
    //    String userId = "1905";
//
    //    LookupInResult result = userCollection.lookupIn(
    //            userId,
    //            Collections.singletonList(get("tasks"))
    //    );
//
    //    var x = result.contentAsArray(0);
    //    var y = result.
    //            System.out.println(x);
    //    System.out.println(y);
//
    //    // System.out.println(x.get(0)); # get 0. element of array -> {"id":91,"text":"ekmek al"}
    //    // System.out.println(x.getObject(0)); # same above
    //    //for (int i = 0; i <= x.size() - 1;i++){
    //    //    System.out.println(x.get(i));
    //    //}
//
    //    // Map<String, Object> mapOfObjects = new HashMap<String, Object>();
    //    return null;
    //}

    @Override
    public Boolean deleteTaskById(int id) {
        LookupInResult result = userCollection.lookupIn(
                userId,
                Collections.singletonList(get("tasks"))
        );
        System.out.println(result);
        System.out.println(result.contentAsArray(0));

        //String str = result.contentAs(0, String.class);
        //System.out.println("getFunc: Altitude = " + str);
        return null;
    }

    @Override
    public Boolean updateTaskById(int id) {
        return null;
    }
}
