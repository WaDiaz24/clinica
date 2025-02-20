package com.api.clinica.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String username,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password
) {
}
