package ru.alex.TODOSpring.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.TODOSpring.dto.TaskDto;
import ru.alex.TODOSpring.entity.Task;
import ru.alex.TODOSpring.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
@AllArgsConstructor
public class TaskController {
    private final TaskService service;

    @GetMapping
    public List<TaskDto> findAll() {
        //todo
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<TaskDto> save(@Valid @RequestBody TaskDto taskDto) {
        TaskDto saved = service.save(taskDto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findById(@PathVariable Integer id) {
        TaskDto task = service.findById(id);
        if (task == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable Integer id, @Valid @RequestBody TaskDto taskDto) {
        TaskDto updated = service.update(id, taskDto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = service.delete(id);
        if (!deleted) return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}
