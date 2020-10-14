package com.example.todoapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String name;
    private Todo todo;

    static class Todo {
        int id;
        String task;

        enum status {DO, DOING, DONE}

    }
}
