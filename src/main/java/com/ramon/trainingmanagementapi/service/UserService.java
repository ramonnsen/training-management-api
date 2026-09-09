package com.ramon.trainingmanagementapi.service;

import com.ramon.trainingmanagementapi.dto.UserCreateDTO;
import com.ramon.trainingmanagementapi.dto.UserResponseDTO;
import com.ramon.trainingmanagementapi.exception.UserAlreadyExistsException;
import com.ramon.trainingmanagementapi.exception.UserNotFoundException;
import com.ramon.trainingmanagementapi.model.User;
import com.ramon.trainingmanagementapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserResponseDTO createUser(UserCreateDTO createDTO) {
        if (userRepository.existsByNameAndPhoneNumber(createDTO.name(), createDTO.phoneNumber())) {
            throw new UserAlreadyExistsException("Invalid User");
        }

        User userBuild = User.builder()
                .name(createDTO.name())
                .phoneNumber(createDTO.phoneNumber())
                .role(createDTO.role())
                .build();

        User savedUser = userRepository.save(userBuild);

        return UserResponseDTO.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .phoneNumber(savedUser.getPhoneNumber())
                .role(savedUser.getRole())
                .build();
    }

    public UserResponseDTO findById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Invalid User"));

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
    }
}
