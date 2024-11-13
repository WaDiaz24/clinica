package com.api.clinica.controller;

import com.api.clinica.domain.dto.DataPatientDTO;
import com.api.clinica.domain.dto.PatientDTO;
import com.api.clinica.domain.service.IPatientService;
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
public class PatientController {

    private final IPatientService patientService;

    @PostMapping("/patient")
    public ResponseEntity<PatientDTO> savePatient(@RequestBody @Valid PatientDTO patientDTO, UriComponentsBuilder uriComponentsBuilder) {
        PatientDTO patient = patientService.save(patientDTO);
        var url = uriComponentsBuilder.path("/v1/api/clinica/patient/{id}").buildAndExpand(patient.id()).toUri();
        return ResponseEntity.created(url).body(patient);
    }

    @GetMapping("/patients")
    public ResponseEntity<Page<DataPatientDTO>> findAllPatients(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(patientService.findAll(pageable));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<DataPatientDTO> findPatientById(@PathVariable Long id) {
        return ResponseEntity.of(patientService.findPatientById(id));
    }

    @PutMapping("/patient/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody @Valid PatientDTO patientDTO) {
        return ResponseEntity.ok(patientService.update(id, patientDTO));
    }

    @DeleteMapping("/patient/{id}")
    public ResponseEntity<Boolean> deletePatientById(@PathVariable Long id) {
        return ResponseEntity.of(patientService.delete(id));
    }
}
