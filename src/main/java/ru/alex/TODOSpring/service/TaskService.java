package ru.alex.TODOSpring.service;

import ru.alex.TODOSpring.dto.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> findAll();
    TaskDto save(TaskDto taskDto);
    TaskDto findById(int id);
    TaskDto update(int id, TaskDto taskDto);
    boolean delete(int id);
}

    /*
    {
    "id": "1",
    "status": "TODO",
    "title": "first_task",
    "description": "first_description",
    "date": "2025-09-01"
}
*/