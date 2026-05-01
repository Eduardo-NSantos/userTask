package com.eduardo.userTask.business;

import com.eduardo.userTask.dto.authenticationDTO.LoginDTO;
import com.eduardo.userTask.dto.authenticationDTO.LoginResponseDTO;
import com.eduardo.userTask.dto.authenticationDTO.RegisterDTO;
import com.eduardo.userTask.infrastructure.entities.User;
import com.eduardo.userTask.infrastructure.repositories.UserRepository;
import com.eduardo.userTask.mapper.AuthenticationMapper;
import com.eduardo.userTask.security.CustomUserDetails;
import com.eduardo.userTask.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final AuthenticationMapper mapper;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public LoginResponseDTO login(LoginDTO login){
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        var auth = authenticationManager.authenticate(usernamePassword);

        if (auth.getPrincipal() == null){
            throw new RuntimeException("Erro ao fazer login");
        }

        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        String token = tokenService.generateToken(user);

        return new LoginResponseDTO(token);
    }

    public void register(RegisterDTO register){
        userService.assertEmailNotInUse(register.getEmail());

        User user = mapper.toEntity(register);
        userRepository.save(user);
    }
}
