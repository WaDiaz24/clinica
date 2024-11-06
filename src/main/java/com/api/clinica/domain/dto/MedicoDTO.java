package com.api.clinica.domain.dto;

import com.api.clinica.domain.data.entities.AddressEntity;
import com.api.clinica.domain.data.entities.Specialty;

public record MedicoDTO(
        Long id,
        String name,
        String email,
        String phone,
        String document,
        Boolean active,
        Specialty speciality,
        AddressEntity address
) {

}
