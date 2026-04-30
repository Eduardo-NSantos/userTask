package com.eduardo.userTask.mapper;

import com.eduardo.userTask.dto.authenticationDTO.RegisterDTO;
import com.eduardo.userTask.infrastructure.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {
    public User toEntity(RegisterDTO register){
        return User.builder()
                .name(register.getName())
                .email(register.getEmail())
                .password(
                        new BCryptPasswordEncoder().encode(register.getPassword())
                )
                .role(register.getRole())
                .build();
    }
}
