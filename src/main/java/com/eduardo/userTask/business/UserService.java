package com.eduardo.userTask.business;

import com.eduardo.userTask.dto.UserDTO.UserRequestDTO;
import com.eduardo.userTask.dto.UserDTO.UserResponseDTO;
import com.eduardo.userTask.dto.UserDTO.UserUpdateDTO;
import com.eduardo.userTask.infrastructure.entities.User;
import com.eduardo.userTask.infrastructure.repositories.UserRepository;
import com.eduardo.userTask.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public User getActiveUserOrThrow(Integer id){
        return repository.findByIdAndDeletedAtIsNull(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado"
                )
        );
    }

    public void assertEmailNotInUse(String email){
        if (repository.existsByEmail(email)){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Email já existente"
            );
        }
    }

    public UserResponseDTO save(UserRequestDTO dto){
        assertEmailNotInUse(dto.getEmail());

        User user = mapper.toEntity(dto);
        User saved = repository.save(user);

        return mapper.toDTO(saved);
    }

    public Page<UserResponseDTO> findAll(
            @PageableDefault(sort = "name") Pageable pageable
    ){
        return repository.findByDeletedAtIsNull(pageable)
                .map(mapper::toDTO);
    }

    public UserResponseDTO find(Integer id){
        User user = getActiveUserOrThrow(id);

        return mapper.toDTO(user);
    }

    @Transactional
    public UserResponseDTO update(Integer id, UserUpdateDTO dto){
        User user = getActiveUserOrThrow(id);

        if (dto.getName() != null){
            user.setName(dto.getName());
        }
        if (dto.getEmail() != null && !dto.getEmail().equals(user.getEmail())){
            assertEmailNotInUse(dto.getEmail());
            user.setEmail(dto.getEmail());
        }

        return mapper.toDTO(user);
    }

    @Transactional
    public void delete(Integer id){
        User user = getActiveUserOrThrow(id);
        user.setDeletedAt(LocalDateTime.now());
    }
}
