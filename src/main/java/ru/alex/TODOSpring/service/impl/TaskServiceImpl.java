package ru.alex.TODOSpring.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.alex.TODOSpring.dto.TaskDto;
import ru.alex.TODOSpring.entity.Task;
import ru.alex.TODOSpring.repository.TaskRepository;
import ru.alex.TODOSpring.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;

    @Override
    public List<TaskDto> findAll() {
        return repository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto save(TaskDto taskDto) {
        Task saved = repository.save(convertToEntity(taskDto));
        return convertToDto(saved);
    }

    @Override
    public TaskDto findById(Integer id) {
        return repository.findById(id).map(this::convertToDto).orElse(null);
    }

    @Override
    public TaskDto update(Integer id, TaskDto taskDto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setTitle(taskDto.getTitle());
                    existing.setDescription(taskDto.getDescription());
                    existing.setStatus(taskDto.getStatus());
                    existing.setDate(taskDto.getDate());
                    return convertToDto(repository.save(existing));
                })
                .orElse(null);
    }

    @Override
    public boolean delete(Integer id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    private TaskDto convertToDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .date(task.getDate())
                .build();
    }

    private Task convertToEntity(TaskDto dto) {
        return Task.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .date(dto.getDate())
                .build();
    }
}
