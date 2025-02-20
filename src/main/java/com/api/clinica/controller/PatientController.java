package com.api.clinica.controller;

import com.api.clinica.domain.dto.DataPatientDTO;
import com.api.clinica.domain.dto.PatientDTO;
import com.api.clinica.domain.service.IPatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/clinica")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    private final IPatientService patientService;

    @Operation(
            summary = "Guardar un paciente"
    )
    @PostMapping("/patient")
    public ResponseEntity<PatientDTO> savePatient(@RequestBody @Valid PatientDTO patientDTO, UriComponentsBuilder uriComponentsBuilder) {
        PatientDTO patient = patientService.save(patientDTO);
        var url = uriComponentsBuilder.path("/v1/api/clinica/patient/{id}").buildAndExpand(patient.id()).toUri();
        return ResponseEntity.created(url).body(patient);
    }

    @Operation(
            summary = "Obtener todos los pacientes"
    )
    @GetMapping("/patients")
    public ResponseEntity<Page<DataPatientDTO>> findAllPatients(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(patientService.findAll(pageable));
    }

    @Operation(
            summary = "Obtener un paciente por id"
    )
    @GetMapping("/patient/{id}")
    public ResponseEntity<DataPatientDTO> findPatientById(@PathVariable Long id) {
        return ResponseEntity.of(patientService.findPatientById(id));
    }

    @Operation(
            summary = "Actualizar un paciente"
    )
    @PutMapping("/patient/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody @Valid PatientDTO patientDTO) {
        return ResponseEntity.ok(patientService.update(id, patientDTO));
    }

    @Operation(
            summary = "Desactivar un paciente"
    )
    @DeleteMapping("/patient/{id}")
    public ResponseEntity<Boolean> deletePatientById(@PathVariable Long id) {
        return ResponseEntity.of(patientService.delete(id));
    }
}
