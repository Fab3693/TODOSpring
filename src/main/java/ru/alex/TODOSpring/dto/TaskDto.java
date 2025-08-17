package ru.alex.TODOSpring.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import ru.alex.TODOSpring.entity.Status;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
