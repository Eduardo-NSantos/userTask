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

    public Task findEntity(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Task não encontrda"
                )
        );
    }

    public TaskResponseDTO create(TaskRequestDTO request){
        User user = userService.getActiveUserOrThrow(request.getUserId());

        Task task = mapper.toEntity(request);
        task.setUser(user);

        Task saved = repository.save(task);
        return mapper.toDTO(saved);
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

    public TaskResponseDTO find(Integer id){
        return mapper.toDTO(findEntity(id));
    }

    public List<TaskResponseDTO> findAllTasksByUser(Integer userId){
        return repository.findByUserId(userId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public TaskResponseDTO findTaskByUser(Integer userId, Integer taskId){
        Task task = repository.findByIdAndUserId(taskId, userId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN, "Essa task não pertence a este usuário"
                )
        );

        return mapper.toDTO(task);
    }

    @Transactional
    public TaskResponseDTO update(Integer id, TaskUpdateDTO update){
        Task task = findEntity(id);

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
    public void delete(Integer id){
        Task task = findEntity(id);

        repository.delete(task);
    }
}
