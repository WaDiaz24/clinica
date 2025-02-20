package com.api.clinica.controller;

import com.api.clinica.domain.dto.DataCancelConsult;
import com.api.clinica.domain.dto.DetailsCancelConsult;
import com.api.clinica.domain.dto.DataDetailsConsult;
import com.api.clinica.domain.dto.DataScheduleConsult;
import com.api.clinica.domain.service.ConsultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/clinica")
@SecurityRequirement(name = "bearer-key")
public class ConsultController {

    private final ConsultService consultService;

    @Operation(
            summary = "Registra una consulta en la base de datos",
            description = ""
    )
    @PostMapping("/consult")
    public ResponseEntity<DataDetailsConsult> scheduleConsult(@RequestBody @Valid DataScheduleConsult dataScheduleConsult) {
        return ResponseEntity.ok(consultService.scheduleConsult(dataScheduleConsult));
    }

    @Operation(
            summary = "Elimina una consulta en la base de datos",
            description = ""
    )
    @PostMapping("/consult/cancel")
    public ResponseEntity<DetailsCancelConsult> cancelConsult(@RequestBody @Valid DataCancelConsult dataCancelConsult){
        return ResponseEntity.ok(consultService.cancelConsult(dataCancelConsult));
    }
}
