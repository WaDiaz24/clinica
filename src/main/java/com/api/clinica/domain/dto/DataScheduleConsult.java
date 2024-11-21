package com.api.clinica.domain.dto;

import com.api.clinica.domain.data.entities.Specialty;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataScheduleConsult(
        Long id,
        @NotNull
        Long idPatient,
        Long idMedico,
        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime consultDate,
        Specialty specialty
) {
}
