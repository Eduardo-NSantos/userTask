package com.eduardo.userTask.controllers;

import com.eduardo.userTask.business.UserService;
import com.eduardo.userTask.dto.UserRequestDTO;
import com.eduardo.userTask.dto.UserResponseDTO;
import com.eduardo.userTask.dto.UserUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService user;

    @PostMapping
    public UserResponseDTO create(@RequestBody UserRequestDTO dto){
        return user.save(dto);
    }

    @GetMapping
    public List<UserResponseDTO> getAll(){
        return user.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDTO get(@PathVariable Integer id){
        return user.find(id);
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable Integer id, @RequestBody UserUpdateDTO dto){
        return user.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        user.delete(id);
    }
}
