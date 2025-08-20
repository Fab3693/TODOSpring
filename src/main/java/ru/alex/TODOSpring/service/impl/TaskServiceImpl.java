package ru.alex.TODOSpring.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.alex.TODOSpring.dto.TaskDto;
import ru.alex.TODOSpring.entity.Task;
import ru.alex.TODOSpring.mapper.TaskMapper;
import ru.alex.TODOSpring.repository.TaskRepository;
import ru.alex.TODOSpring.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;

    @Override
    public TaskDto save(TaskDto taskDto) {
        Task saved = repository.save(mapper.toEntity(taskDto));
        return mapper.toDto(saved);
    }

    @Override
    public List<TaskDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto findById(int id) {
        return repository.findById(id).map(mapper::toDto).orElse(null);
    }

    @Override
    public TaskDto update(int id, TaskDto taskDto) {
        Task task = repository.getReferenceById(id);
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setDate(taskDto.getDate());
        Task saved = repository.save(task);
        return mapper.toDto(saved);
    }

    @Override
    public boolean delete(int id) {
        return repository.deleteById(id) > 0;
    }
}
