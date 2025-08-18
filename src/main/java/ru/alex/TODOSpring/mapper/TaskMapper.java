package ru.alex.TODOSpring.mapper;

import org.mapstruct.Mapper;
import ru.alex.TODOSpring.dto.TaskDto;
import ru.alex.TODOSpring.entity.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);
    Task toEntity(TaskDto dto);
}
