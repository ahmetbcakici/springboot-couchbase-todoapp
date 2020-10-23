package com.example.todoapp.model;

import com.example.todoapp.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {
    int id;
    String text;
    Status status;
}

