package ru.alex.TODOSpring.service;

import ru.alex.TODOSpring.entity.Task;

import java.util.List;


public interface TaskService {
    List<Task> findAll ();
    Task save(Task task);
    Task findByTitle(String name);
    Task update(Task task);
    void deleteByTitle(String name);
}
//