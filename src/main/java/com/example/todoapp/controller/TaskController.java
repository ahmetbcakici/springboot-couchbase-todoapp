package com.example.todoapp.controller;

import com.example.todoapp.model.Task;
import com.example.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        System.out.println("/task get");
        return ResponseEntity.ok(taskRepository.findUserTasks());
    }

    @DeleteMapping("/{id}")
    public Boolean deleteTaskById(@PathVariable Integer id) {
        return taskRepository.deleteTaskById(id);
    }
}
