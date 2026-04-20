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

    public TaskResponseDTO create(Integer userId, TaskRequestDTO dto){
        User user = userService.getActiveUserOrThrow(userId);

        Task task = mapper.toEntity(dto);
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

    @Transactional
    public TaskResponseDTO update(Integer id, TaskUpdateDTO dto){
        Task task = findEntity(id);

        if (dto.getTitle() != null){
            task.setTitle(dto.getTitle());
        }
        if (dto.getDescription() != null){
            task.setDescription(dto.getDescription());
        }
        if (dto.getStatus() != null){
            task.setStatus(dto.getStatus());
        }
        if (dto.getDueDate() != null){
            task.setDueDate(dto.getDueDate());
        }
        if (dto.getPriority() != null){
            task.setPriority(dto.getPriority());
        }

        return mapper.toDTO(task);
    }

    @Transactional
    public void delete(Integer id){
        Task task = findEntity(id);

        repository.delete(task);
    }
}
