package com.eduardo.userTask.dto.authenticationDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
