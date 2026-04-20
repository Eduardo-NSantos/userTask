package com.eduardo.userTask.controllers;

import com.eduardo.userTask.business.TaskService;
import com.eduardo.userTask.dto.TaskDTO.TaskRequestDTO;
import com.eduardo.userTask.dto.TaskDTO.TaskResponseDTO;
import com.eduardo.userTask.dto.TaskDTO.TaskUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService task;

    @PostMapping("/{userId}")
    public ResponseEntity<TaskResponseDTO> create(
            @PathVariable Integer userId,
            @RequestBody @Valid TaskRequestDTO dto
    ){
        TaskResponseDTO saved = task.create(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAll(){
        return ResponseEntity.ok(task.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> get(@PathVariable Integer id){
        return ResponseEntity.ok(task.find(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> update(
            @PathVariable Integer id,
            @RequestBody @Valid TaskUpdateDTO dto
    ){
        return ResponseEntity.ok(task.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        task.delete(id);
        return ResponseEntity.noContent().build();
    }
}
