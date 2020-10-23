package com.example.todoapp.repository;

import com.example.todoapp.model.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> findUserTasks();

    Task findTaskById(int id);

    Task addNewTask(Task task);

    Boolean deleteTaskById(int id);

    Boolean updateTaskById(int id);
}
