package com.sylvester.ecommerce.mapper;

import com.sylvester.ecommerce.dto.UserRequest;
import com.sylvester.ecommerce.dto.UserResponse;
import com.sylvester.ecommerce.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UserMapper {




    public static User toEntity(UserRequest userRequest, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole("ROLE_USER");

        return user;
    }

    public static UserResponse toDTO(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());
        userResponse.setRole(user.getRole());
        return userResponse;
    }
}
