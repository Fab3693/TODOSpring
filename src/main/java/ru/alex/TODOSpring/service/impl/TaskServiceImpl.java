package ru.alex.TODOSpring.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import ru.alex.TODOSpring.entity.Task;
import ru.alex.TODOSpring.repository.TaskRepository;
import ru.alex.TODOSpring.service.TaskService;
import java.util.List;

@Primary
@org.springframework.stereotype.Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;

    @Override
    public List<Task> findAll() {
        return repository.findAll();
    }

    @Override
    public Task save(Task task) {
        return repository.save(task);
    }

    @Override
    public Task findByTitle(String name) {
        return repository.findByTitle(name);
    }

    @Override
    public Task update(Task task) {
        return repository.save(task);
    }

    @Override
    public void deleteByTitle(String name) {
        repository.deleteByTitle(name);
    }
}
