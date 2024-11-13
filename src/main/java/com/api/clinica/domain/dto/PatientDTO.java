package com.api.clinica.domain.dto;

import com.api.clinica.domain.data.entities.AddressEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PatientDTO(
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String document,
        @NotBlank
        String phone,
        Boolean active,
        @NotNull
        LocalDate birthDate,
        Integer age,
        @NotNull
        AddressEntity address
) {
}
