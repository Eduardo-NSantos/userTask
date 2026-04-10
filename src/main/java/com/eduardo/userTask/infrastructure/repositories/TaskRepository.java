package com.eduardo.userTask.infrastructure.repositories;

import com.eduardo.userTask.infrastructure.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
