package com.eduardo.userTask.controllers;

import com.eduardo.userTask.business.TaskService;
import com.eduardo.userTask.dto.TaskDTO.TaskRequestDTO;
import com.eduardo.userTask.dto.TaskDTO.TaskResponseDTO;
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

    @PostMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> create(
            @PathVariable Integer id,
            @RequestBody TaskRequestDTO dto
    ){
        TaskResponseDTO saved = task.create(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(task.create(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAll(){
        return ResponseEntity.ok(task.findAll());
    }
}
