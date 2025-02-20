package com.api.clinica.domain.validations;

import com.api.clinica.domain.dto.DataScheduleConsult;

public interface QueryValidator {
    void validate(DataScheduleConsult data);
}
