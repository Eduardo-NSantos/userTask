package com.eduardo.userTask.config;

import com.eduardo.userTask.business.UserService;
import com.eduardo.userTask.dto.UserDTO.UserRequestDTO;
import com.eduardo.userTask.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateFirstAdmin {
    private final UserRepository userRepository;
    private final UserService userService;
    private final AdminProperties admin;

    public void seed() {
        if (!userRepository.existsByEmail(admin.getEmail())) {
            var user = new UserRequestDTO();
            user.setName(admin.getName());
            user.setEmail(admin.getEmail());
            user.setPassword(admin.getPassword());
            user.setRole(admin.getRole());

            userService.save(user);
        }
    }
}
