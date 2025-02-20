package com.api.clinica.domain.dto;

import com.api.clinica.domain.data.entities.CancellationReason;

public record DataCancelConsult(
        Long id,
        CancellationReason cancellationMotive
) {
}
