package com.api.clinica.domain.service;

import com.api.clinica.domain.dto.DataPatientDTO;
import com.api.clinica.domain.dto.PatientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPatientService {
    PatientDTO save(PatientDTO patient);
    Page<DataPatientDTO> findAll (Pageable pageable);
    Optional<DataPatientDTO> findPatientById(Long id);
    PatientDTO update(Long id, PatientDTO patient);
    Optional<Boolean> delete(Long id);
}
