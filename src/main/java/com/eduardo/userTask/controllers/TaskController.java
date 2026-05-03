package com.eduardo.userTask.controllers;

import com.eduardo.userTask.business.TaskService;
import com.eduardo.userTask.dto.TaskDTO.TaskRequestDTO;
import com.eduardo.userTask.dto.TaskDTO.TaskResponseDTO;
import com.eduardo.userTask.dto.TaskDTO.TaskUpdateDTO;
import com.eduardo.userTask.security.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService task;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid TaskRequestDTO request
    ){
        TaskResponseDTO saved = task.create(userDetails.getId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAll(){
        return ResponseEntity.ok(task.findAll());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> get(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Integer taskId
    ){
        return ResponseEntity.ok(task.find(userDetails.getId(), taskId));
    }

    @GetMapping("my-tasks")
    public ResponseEntity<List<TaskResponseDTO>> getAllByUser(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        List<TaskResponseDTO> tasks = task.findAllTasksByUser(userDetails.getId());
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> update(
            @PathVariable Integer taskId,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid TaskUpdateDTO update
    ){
        return ResponseEntity.ok(task.update(taskId, userDetails.getId(), update));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Integer taskId
    ){
        task.delete(taskId, userDetails.getId());
        return ResponseEntity.noContent().build();
    }
}
