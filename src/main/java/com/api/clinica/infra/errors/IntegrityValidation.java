package com.api.clinica.infra.errors;

public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String message) {
        super(message);
    }
}
