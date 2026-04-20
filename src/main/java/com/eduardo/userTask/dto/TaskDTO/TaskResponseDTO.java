package com.eduardo.userTask.dto.TaskDTO;

import com.eduardo.userTask.enums.TaskPriority;
import com.eduardo.userTask.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TaskResponseDTO {
    @NotBlank
    private Integer id;

    @NotBlank
    private Integer userId;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private TaskStatus status;

    private LocalDateTime dueDate;

    @NotBlank
    private TaskPriority priority;
}
