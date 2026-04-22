package com.eduardo.userTask.dto.TaskDTO;

import com.eduardo.userTask.enums.TaskPriority;
import com.eduardo.userTask.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskRequestDTO {
    @NotBlank
    private String title;

    private String description;

    private TaskStatus status;

    private LocalDateTime dueDate;

    @NotNull
    private TaskPriority priority;

    private Integer userId;
}
