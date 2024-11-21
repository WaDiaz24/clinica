package com.api.clinica.domain.dto;

import com.api.clinica.domain.data.entities.ConsultEntity;

import java.time.LocalDateTime;

public record DataDetailsConsult(
        Long id,
        String namePatient,
        String nameMedico,
        LocalDateTime consultDate
) {

    public DataDetailsConsult(ConsultEntity consult) {
        this(consult.getId(),
            consult.getPatient().getName(),
            consult.getMedico().getName(),
            consult.getConsultDate());
    }
}
