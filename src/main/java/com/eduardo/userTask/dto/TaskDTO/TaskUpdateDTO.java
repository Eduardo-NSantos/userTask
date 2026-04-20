package com.eduardo.userTask.dto.TaskDTO;

import com.eduardo.userTask.enums.TaskPriority;
import com.eduardo.userTask.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskUpdateDTO {
    private String title;

    private String description;

    private TaskStatus status;

    private LocalDateTime dueDate;

    private TaskPriority priority;
}
