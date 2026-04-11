package com.eduardo.userTask.business;

import com.eduardo.userTask.dto.UserDTO.UserRequestDTO;
import com.eduardo.userTask.dto.UserDTO.UserResponseDTO;
import com.eduardo.userTask.dto.UserDTO.UserUpdateDTO;
import com.eduardo.userTask.infrastructure.entities.User;
import com.eduardo.userTask.infrastructure.repositories.UserRepository;
import com.eduardo.userTask.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public User findEntity(Integer id){
        User user = repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado"
                )
        );

        if (user.getDeletedAt() != null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Usuário deletado"
            );
        }

        return user;
    }

    public UserResponseDTO save(UserRequestDTO dto){
        User user = mapper.toEntity(dto);
        User saved = repository.save(user);
        return mapper.toDTO(saved);
    }

    public List<UserResponseDTO> findAll(){
        return repository.findByDeletedAtIsNull()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public UserResponseDTO find(Integer id){
        User user = findEntity(id);

        return mapper.toDTO(user);
    }

    @Transactional
    public UserResponseDTO update(Integer id, UserUpdateDTO dto){
        User user = findEntity(id);

        if (dto.getName() != null){
            user.setName(dto.getName());
        }
        if (dto.getEmail() != null){
            user.setEmail(dto.getEmail());
        }

        User updated = repository.save(user);

        return mapper.toDTO(updated);
    }

    @Transactional
    public void delete(Integer id){
        User user = findEntity(id);

        user.setDeletedAt(LocalDateTime.now());

        repository.save(user);
    }
}
