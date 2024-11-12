package com.api.clinica.domain.dto;

import com.api.clinica.domain.data.entities.AddressEntity;
import com.api.clinica.domain.data.entities.Specialty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoDTO(
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String phone,
        @NotBlank
        @Pattern(regexp = "\\d{4,10}", message = "Debe contener solo números y tener entre 4 y 10 dígitos.")
        String document,
        Boolean active,
        @NotNull
        Specialty speciality,
        @NotNull
        AddressEntity address
) {

}
