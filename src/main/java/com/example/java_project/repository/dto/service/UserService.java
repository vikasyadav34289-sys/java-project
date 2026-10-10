package com.example.java_project.service;

import com.example.java_project.dto.RegisterRequest;
import com.example.java_project.dto.UserResponse;
import com.example.java_project.model.User;
import com.example.java_project.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse registerUser(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(
                "Email is already registered"
            );
        }

        User user = new User(
            request.getName(),
            request.getEmail(),
            request.getPassword()
        );

        User savedUser = userRepository.save(user);

        return new UserResponse(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getEmail()
        );
    }
}