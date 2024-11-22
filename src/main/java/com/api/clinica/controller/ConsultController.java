package com.api.clinica.controller;

import com.api.clinica.domain.dto.DataDetailsConsult;
import com.api.clinica.domain.dto.DataScheduleConsult;
import com.api.clinica.domain.service.ConsultService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/clinica")
@SecurityRequirement(name = "bearer-key")
public class ConsultController {

    private final ConsultService consultService;

    @PostMapping("/consult")
    public ResponseEntity<DataDetailsConsult> scheduleConsult(@RequestBody @Valid DataScheduleConsult dataScheduleConsult) {
        return ResponseEntity.ok(consultService.scheduleConsult(dataScheduleConsult));
    }
}
