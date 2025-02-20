package com.api.clinica.domain.dto;

public record DetailsCancelConsult(
        String namePatient,
        String nameMedico,
        String cancellationMotive
) {
}
