package com.sylvester.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email required")
    @Email(message = "Email must be a valid email")
    private String email;
    @NotBlank(message = "Password required")
    private String password;
}
