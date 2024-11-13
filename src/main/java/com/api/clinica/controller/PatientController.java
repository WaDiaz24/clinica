package com.api.clinica.controller;

import com.api.clinica.domain.dto.PatientDTO;
import com.api.clinica.domain.service.IPatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/clinica")
public class PatientController {

    private final IPatientService patientService;

    @PostMapping("/patient")
    public ResponseEntity<PatientDTO> savePatient(@RequestBody @Valid PatientDTO patientDTO, UriComponentsBuilder uriComponentsBuilder) {
        PatientDTO patient = patientService.save(patientDTO);
        var url = uriComponentsBuilder.path("/v1/api/clinica/patient/{id}").buildAndExpand(patient.id()).toUri();
        return ResponseEntity.created(url).body(patient);
    }
}
