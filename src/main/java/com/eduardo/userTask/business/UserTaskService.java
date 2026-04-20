package com.eduardo.userTask.business;

import com.eduardo.userTask.dto.TaskDTO.TaskResponseDTO;
import com.eduardo.userTask.infrastructure.entities.Task;
import com.eduardo.userTask.infrastructure.repositories.TaskRepository;
import com.eduardo.userTask.mapper.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class UserTaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TaskResponseDTO> findAllTasksByUser(Integer userId){
        return taskRepository.findByUserId(userId)
                .stream()
                .map(taskMapper::toDTO)
                .toList();
    }

    public TaskResponseDTO findTaskByUser(Integer userId, Integer taskId){
        Task task = taskRepository.findByIdAndUserId(taskId, userId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN, "Essa task não pertence a este usuário"
                )
        );

        return taskMapper.toDTO(task);
    }
}
