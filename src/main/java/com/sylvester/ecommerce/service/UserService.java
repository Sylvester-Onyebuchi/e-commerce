package com.sylvester.ecommerce.service;

import com.sylvester.ecommerce.dto.UserRequest;
import com.sylvester.ecommerce.dto.UserResponse;
import com.sylvester.ecommerce.entity.User;
import com.sylvester.ecommerce.exception.UserAlreadyExistException;
import com.sylvester.ecommerce.exception.UserNotFoundException;
import com.sylvester.ecommerce.mapper.UserMapper;
import com.sylvester.ecommerce.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserResponse> getAllUsers() {
        List<User> user = userRepository.findAll();
        return user.stream()
                .map(UserMapper::toDTO).toList();
    }

    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("User not found")
        );
        return UserMapper.toDTO(user);
    }

    public UserResponse createUser(UserRequest userRequest) {
        boolean exist = userRepository.existsByEmailIgnoreCase(userRequest.getEmail());
        if (exist) {
            throw new UserAlreadyExistException("User already exist!");
        }
        User user = UserMapper.toEntity(userRequest, passwordEncoder);
        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }

    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("User not found")
        );
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("User not found")
        );
        userRepository.delete(user);
    }
}
