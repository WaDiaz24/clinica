package com.api.clinica.domain.dto;

import java.time.LocalDate;

public record DataDetailsConsult(
        Long id,
        Long idPatient,
        Long idMedico,
        LocalDate consultDate
) {

}
