package com.eduardo.userTask.controllers;

import com.eduardo.userTask.business.TaskService;
import com.eduardo.userTask.dto.TaskDTO.TaskResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usertask/{userId}")
@RequiredArgsConstructor
public class UserTaskController {
    private final TaskService task;

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
