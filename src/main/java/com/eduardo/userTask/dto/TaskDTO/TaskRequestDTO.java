package com.eduardo.userTask.dto.TaskDTO;

import com.eduardo.userTask.enums.TaskPriority;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskRequestDTO {
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private TaskPriority priority;
}
