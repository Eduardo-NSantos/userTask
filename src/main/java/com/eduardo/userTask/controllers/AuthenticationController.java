package com.eduardo.userTask.controllers;

import com.eduardo.userTask.business.AuthenticationService;
import com.eduardo.userTask.dto.authenticationDTO.LoginDTO;
import com.eduardo.userTask.dto.authenticationDTO.LoginResponseDTO;
import com.eduardo.userTask.dto.authenticationDTO.RegisterDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService auth;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO login){
        LoginResponseDTO token = auth.login(login);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO register){
        auth.register(register);
        return ResponseEntity.ok().build();
    }
}
