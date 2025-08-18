package ru.alex.TODOSpring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.alex.TODOSpring.dto.TaskDto;
import ru.alex.TODOSpring.entity.Status;
import ru.alex.TODOSpring.service.TaskService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TaskControllerTest {

    private MockMvc mockMvc;
    private TaskService service;
    private ObjectMapper objectMapper;
    private TaskDto dto;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(TaskService.class);
        TaskController controller = new TaskController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();

        dto = TaskDto.builder()
                .id(1)
                .title("Test task")
                .description("Test description")
                .status(Status.TODO)
                .date(LocalDate.of(2025, 9, 1))
                .build();
    }

    @Test
    void findAll() throws Exception {
        Mockito.when(service.findAll()).thenReturn(List.of(dto));
        mockMvc.perform(get("/api/v1/task"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test task"));
    }

    @Test
    void save() throws Exception {
        Mockito.when(service.save(any(TaskDto.class))).thenReturn(dto);
        mockMvc.perform(post("/api/v1/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test task"));
    }

    @Test
    void findById() throws Exception {
        TaskDto specificDto = TaskDto.builder()
                .title("Found task")
                .status(Status.IN_PROGRESS)
                .date(LocalDate.of(2025, 9, 2))
                .build();

        Mockito.when(service.findById(1)).thenReturn(specificDto);
        mockMvc.perform(get("/api/v1/task/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Found task"));
    }

    @Test
    void update() throws Exception {
        TaskDto updatedDto = TaskDto.builder()
                .title("Updated task")
                .status(Status.DONE)
                .date(LocalDate.of(2025, 9, 3))
                .build();

        Mockito.when(service.update(eq(1), any(TaskDto.class))).thenReturn(updatedDto);
        mockMvc.perform(put("/api/v1/task/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated task"));
    }

    @Test
    void delete() throws Exception {
        Mockito.when(service.delete(1)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/task/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_NotFound() throws Exception {
        Mockito.when(service.delete(99)).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/task/99"))
                .andExpect(status().isNotFound());
    }
}