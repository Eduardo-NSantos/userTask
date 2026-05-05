package com.eduardo.userTask.dto.userDTO;

import com.eduardo.userTask.enums.UserRole;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    private String name;

    @Email
    private String email;

    private UserRole role;
}
