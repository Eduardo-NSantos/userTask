package com.eduardo.userTask.business;

import com.eduardo.userTask.dto.TaskDTO.TaskResponseDTO;
import com.eduardo.userTask.infrastructure.entities.Task;
import com.eduardo.userTask.mapper.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class UserTaskService {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public List<TaskResponseDTO> findAllTasksByUser(Integer userId){
        return taskService.findAll()
                .stream()
                .filter(task -> task.getUserId().equals(userId))
                .toList();
    }

    public TaskResponseDTO findTaskByUser(Integer userId, Integer taskId){
        Task task = taskService.findEntity(taskId);

        if (!task.getUser().getId().equals(userId)){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Essa task não pertence a este usuário"
            );
        }

        return taskMapper.toDTO(task);
    }
}
