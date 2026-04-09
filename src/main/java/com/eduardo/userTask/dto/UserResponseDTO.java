package com.eduardo.userTask.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
}
