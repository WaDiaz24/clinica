package com.api.clinica.domain.dto;

import com.api.clinica.domain.data.entities.Specialty;

public record DataMedicoDTO(
        String name,
        String email,
        String phone,
        String document,
        Specialty speciality
) {

}
