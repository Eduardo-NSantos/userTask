package com.eduardo.userTask.infrastructure.repositories;

import com.eduardo.userTask.infrastructure.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUserId(Integer id);
    Optional<Task> findByIdAndUserId(Integer taskId, Integer userId);
}
