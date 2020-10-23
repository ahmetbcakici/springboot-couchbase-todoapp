package com.example.todoapp.controller;

import com.example.todoapp.model.Task;
import com.example.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        System.out.println("/task get");
        var x = taskRepository.findUserTasks();
        System.out.println(x);
        //return x;
        return x;
    }

    @GetMapping("/{id}")
    public Task findTaskById(@PathVariable Integer id) {
        return taskRepository.findTaskById(id);
    }

    @PostMapping()
    public Task findTaskById(@RequestBody Task task) {
        return taskRepository.addNewTask(task);
    }

    @PatchMapping("/{id}")
    public Boolean updateTaskById(@PathVariable Integer id) {
        return taskRepository.updateTaskById(id);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteTaskById(@PathVariable Integer id) {
        return taskRepository.deleteTaskById(id);
    }
}
