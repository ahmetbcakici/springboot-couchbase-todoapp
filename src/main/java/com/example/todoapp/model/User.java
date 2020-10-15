package com.example.todoapp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {
    private int id;
    private String name;
    private String surname;
    private List<Task> tasks = new ArrayList<Task>();
}

