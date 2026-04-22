package com.eduardo.userTask.controllers;

import com.eduardo.userTask.business.TaskService;
import com.eduardo.userTask.dto.TaskDTO.TaskRequestDTO;
import com.eduardo.userTask.dto.TaskDTO.TaskResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usertask/{userId}")
@RequiredArgsConstructor
public class UserTaskController {
    private final TaskService task;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(
            @PathVariable Integer userId,
            @RequestBody @Valid TaskRequestDTO request
    ){
        TaskResponseDTO saved = task.create(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> tasksByUser(@PathVariable Integer userId){
        List<TaskResponseDTO> tasks = task.findAllTasksByUser(userId);

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponseDTO> taskByUser(
            @PathVariable Integer userId,
            @PathVariable Integer taskId
    ){
        return ResponseEntity.ok(task.findTaskByUser(userId, taskId));
    }
}
