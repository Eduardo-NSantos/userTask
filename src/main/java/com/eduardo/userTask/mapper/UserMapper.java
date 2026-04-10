package com.eduardo.userTask.mapper;

import com.eduardo.userTask.dto.UserDTO.UserRequestDTO;
import com.eduardo.userTask.dto.UserDTO.UserResponseDTO;
import com.eduardo.userTask.infrastructure.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequestDTO dto){
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public UserResponseDTO toDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
