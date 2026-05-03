package com.eduardo.userTask.business;

import com.eduardo.userTask.dto.TaskDTO.TaskRequestDTO;
import com.eduardo.userTask.dto.TaskDTO.TaskResponseDTO;
import com.eduardo.userTask.dto.TaskDTO.TaskUpdateDTO;
import com.eduardo.userTask.infrastructure.entities.Task;
import com.eduardo.userTask.infrastructure.entities.User;
import com.eduardo.userTask.infrastructure.repositories.TaskRepository;
import com.eduardo.userTask.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final TaskMapper mapper;
    private final UserService userService;

    public Task getEntityOrThrow(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Task não encontrda"
                )
        );
    }

    public TaskResponseDTO create(Integer userId, TaskRequestDTO request){
        User user = userService.getActiveUserOrThrow(userId);

        Task task = mapper.toEntity(request);
        task.setUser(user);

        Task saved = repository.save(task);
        return mapper.toDTO(saved);
    }

    public List<TaskResponseDTO> findAll(){
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public List<TaskResponseDTO> findAllTasksByUser(Integer userId){
        return repository.findByUserId(userId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public TaskResponseDTO find(Integer userId, Integer taskId){
        Task task = repository.findByIdAndUserId(taskId, userId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN, "Você não possui acesso a essa task"
                )
        );

        return mapper.toDTO(task);
    }

    @Transactional
    public TaskResponseDTO update(Integer taskId, Integer userId, TaskUpdateDTO update){
        Task task = getEntityOrThrow(taskId);

        if (!task.getUser().getId().equals(userId)){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Você não possui acesso a essa task"
            );
        }

        if (update.getTitle() != null){
            task.setTitle(update.getTitle());
        }
        if (update.getDescription() != null){
            task.setDescription(update.getDescription());
        }
        if (update.getStatus() != null){
            task.setStatus(update.getStatus());
        }
        if (update.getDueDate() != null){
            task.setDueDate(update.getDueDate());
        }
        if (update.getPriority() != null){
            task.setPriority(update.getPriority());
        }

        return mapper.toDTO(task);
    }

    @Transactional
    public void delete(Integer taskId, Integer userId){
        Task task = getEntityOrThrow(taskId);

        if (!task.getUser().getId().equals(userId)){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Você não possui acesso a essa task"
            );
        }

        repository.delete(task);
    }
}
