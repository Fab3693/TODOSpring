package ru.alex.TODOSpring.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.alex.TODOSpring.dto.TaskDto;
import ru.alex.TODOSpring.entity.Status;
import ru.alex.TODOSpring.entity.Task;
import ru.alex.TODOSpring.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskServiceImpl service;

    @Test
    void findAll_ShouldReturnTasks() {
        Task task = Task.builder()
                .id(1)
                .title("Test task")
                .description("Test description")
                .status(Status.TODO)
                .date(LocalDate.of(2025, 9, 1))
                .build();

        when(repository.findAll()).thenReturn(List.of(task));
        List<TaskDto> result = service.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Test task");
    }

    @Test
    void save_ShouldReturnSavedTask() {
        Task task = Task.builder()
                .id(1)
                .title("New task")
                .description("New description")
                .status(Status.TODO)
                .date(LocalDate.of(2025, 9, 1))
                .build();
        when(repository.save(any(Task.class))).thenReturn(task);
        TaskDto dto = TaskDto.builder()
                .title("New task")
                .description("New description")
                .status(Status.TODO)
                .date(LocalDate.of(2025, 9, 1))
                .build();
        TaskDto result = service.save(dto);
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getTitle()).isEqualTo("New task");
    }

    @Test
    void findById_ShouldReturnTask_WhenExists() {
        Task task = Task.builder()
                .id(1)
                .title("Found task")
                .description("Found description")
                .status(Status.IN_PROGRESS)
                .date(LocalDate.of(2025, 9, 2))
                .build();
        when(repository.findById(1)).thenReturn(Optional.of(task));
        TaskDto result = service.findById(1);
        assertThat(result.getTitle()).isEqualTo("Found task");
    }

    @Test
    void findById_ShouldThrow_WhenNotFound() {
        when(repository.findById(99)).thenReturn(Optional.empty());
        org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class,
                () -> service.findById(99)
        );
    }

    @Test
    void update_ShouldReturnUpdatedTask() {
        Task task = Task.builder()
                .id(1)
                .title("Old task")
                .description("Old desc")
                .status(Status.TODO)
                .date(LocalDate.of(2025, 9, 1))
                .build();
        Task updatedTask = Task.builder()
                .id(1)
                .title("Updated task")
                .description("Updated desc")
                .status(Status.DONE)
                .date(LocalDate.of(2025, 9, 3))
                .build();

        when(repository.findById(1)).thenReturn(Optional.of(task));
        when(repository.save(any(Task.class))).thenReturn(updatedTask);
        TaskDto dto = TaskDto.builder()
                .title("Updated task")
                .description("Updated desc")
                .status(Status.DONE)
                .date(LocalDate.of(2025, 9, 3))
                .build();
        TaskDto result = service.update(1, dto);
        assertThat(result.getTitle()).isEqualTo("Updated task");
        assertThat(result.getStatus()).isEqualTo(Status.DONE);
    }

    @Test
    void delete_ShouldReturnTrue_WhenTaskExists() {
        Task task = Task.builder().id(1).title("Delete me").build();
        when(repository.findById(1)).thenReturn(Optional.of(task));
        boolean result = service.delete(1);
        verify(repository, times(1)).delete(task);
        assertThat(result).isTrue();
    }

    @Test
    void delete_ShouldReturnFalse_WhenTaskNotExists() {
        when(repository.findById(99)).thenReturn(Optional.empty());
        boolean result = service.delete(99);
        verify(repository, never()).delete(any(Task.class));
        assertThat(result).isFalse();
    }
}
