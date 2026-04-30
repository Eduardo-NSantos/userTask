package com.eduardo.userTask.dto.UserDTO;

import com.eduardo.userTask.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserResponseDTO {
    @NotBlank
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private LocalDateTime createdAt;

    @NotNull
    private UserRole role;
}
