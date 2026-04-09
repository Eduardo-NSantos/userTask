package com.eduardo.userTask.infrastructure.repositories;

import com.eduardo.userTask.infrastructure.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByDeletedAtIsNull();
}
