package com.api.clinica.domain.dto;

public record DataPatientDTO(
        String name,
        String email,
        String document,
        String phone,
        Integer age
) {
}
