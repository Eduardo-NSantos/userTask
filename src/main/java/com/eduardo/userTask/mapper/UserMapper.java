package com.eduardo.userTask.mapper;

import com.eduardo.userTask.dto.UserDTO.UserRequestDTO;
import com.eduardo.userTask.dto.UserDTO.UserResponseDTO;
import com.eduardo.userTask.infrastructure.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequestDTO request){
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        new BCryptPasswordEncoder().encode(request.getPassword())
                )
                .role(request.getRole())
                .build();
    }

    public UserResponseDTO toDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .role(user.getRole())
                .build();
    }
}
