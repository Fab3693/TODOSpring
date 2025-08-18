package ru.alex.TODOSpring.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import ru.alex.TODOSpring.entity.Status;
import java.time.LocalDate;

@Data
@Builder
public class TaskDto {
    private Integer id;

    @NotBlank
    @Size(max = 255)
    private String title;

    private String description;

    @NotNull
    private LocalDate date;

    @NotNull
    private Status status;
}
