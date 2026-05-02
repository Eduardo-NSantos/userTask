package com.eduardo.userTask.controllers;

import com.eduardo.userTask.business.UserService;
import com.eduardo.userTask.dto.UserDTO.UserRequestDTO;
import com.eduardo.userTask.dto.UserDTO.UserResponseDTO;
import com.eduardo.userTask.dto.UserDTO.UserUpdateDTO;
import com.eduardo.userTask.security.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService user;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(
            @RequestBody @Valid UserRequestDTO request
    ){
        UserResponseDTO response = user.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAll(Pageable pageable){
        return ResponseEntity.ok(user.findAll(pageable));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> get(@AuthenticationPrincipal CustomUserDetails userDetails){
        UserResponseDTO response = user.find(userDetails.getId());
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<UserResponseDTO> update(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid UserUpdateDTO update
    ){
        UserResponseDTO response = user.update(userDetails.getId(), update);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@AuthenticationPrincipal CustomUserDetails userDetails){
        user.delete(userDetails.getId());
        return ResponseEntity.noContent().build();
    }
}
