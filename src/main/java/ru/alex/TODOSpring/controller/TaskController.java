package ru.alex.TODOSpring.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.alex.TODOSpring.entity.Task;
import ru.alex.TODOSpring.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@AllArgsConstructor
public class TaskController {
    private final TaskService service;

    @GetMapping
    public List<Task> findAll() {
        //todo
        return service.findAll();
    }

    @PostMapping
    public Task save(@RequestBody Task task) {
        return service.save(task);
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

    @GetMapping("/{name}")
    public Task findByName(@PathVariable String name) {
        return service.findByTitle(name);
    }

    @PutMapping
    public Task update(@RequestBody Task task) {
        return service.update(task);
    }

    @DeleteMapping("/{name}")
    public void deleteByTitle(@PathVariable String name) {
        service.deleteByTitle(name);
    }
}
