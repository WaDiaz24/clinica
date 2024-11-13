package com.api.clinica.domain.service;

import com.api.clinica.domain.data.entities.PatientEntity;
import com.api.clinica.domain.data.repositories.IPatientRepository;
import com.api.clinica.domain.dto.DataPatientDTO;
import com.api.clinica.domain.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PatientService implements IPatientService{

    private final IPatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientDTO save(PatientDTO patient) {
        PatientEntity patientE = patientMapper.convertPatientDTOToPatientEntity(patient);
        Integer age = calculateAge(patientE.getBirthDate());
        patientE.setAge(age);
        return patientMapper.convertPatientEntityToPatientDTO(patientRepository.save(patientE));
    }

    @Override
    public Page<DataPatientDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<DataPatientDTO> findMedicoById(Long id) {
        return Optional.empty();
    }

    @Override
    public PatientDTO update(Long id, PatientDTO patient) {
        return null;
    }

    @Override
    public Optional<Boolean> delete(Long id) {
        return Optional.empty();
    }

    public Integer calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            return 0;
        }
        LocalDate today = LocalDate.now();
        return Period.between(birthDate, today).getYears();
    }
}
