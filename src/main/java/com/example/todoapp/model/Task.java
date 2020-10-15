package com.example.todoapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {
    int id;
    String text;
    Status status;
}

enum Status {DO, DOING, DONE}