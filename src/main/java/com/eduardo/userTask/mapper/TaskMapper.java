package com.eduardo.userTask.mapper;

import com.eduardo.userTask.dto.TaskDTO.TaskRequestDTO;
import com.eduardo.userTask.dto.TaskDTO.TaskResponseDTO;
import com.eduardo.userTask.infrastructure.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public Task toEntity(TaskRequestDTO request){
        return Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .status(request.getStatus())
                .priority(request.getPriority())
                .build();
    }

    public TaskResponseDTO toDTO(Task task){
        return TaskResponseDTO.builder()
                .id(task.getId())
                .userId(task.getUser().getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .dueDate(task.getDueDate())
                .priority(task.getPriority())
                .build();
    }
}
