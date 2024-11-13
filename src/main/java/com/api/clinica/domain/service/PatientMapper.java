package com.api.clinica.domain.service;

import com.api.clinica.domain.data.entities.PatientEntity;
import com.api.clinica.domain.dto.DataPatientDTO;
import com.api.clinica.domain.dto.PatientDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class PatientMapper {

    public PatientEntity convertPatientDTOToPatientEntity(PatientDTO patientDTO) {
        return new PatientEntity(
                patientDTO.id(),
                patientDTO.name(),
                patientDTO.email(),
                patientDTO.document(),
                patientDTO.phone(),
                patientDTO.active() != null ? patientDTO.active() : true,
                patientDTO.birthDate(),
                patientDTO.age(),
                patientDTO.address()
        );
    }

    public PatientDTO convertPatientEntityToPatientDTO(PatientEntity patientEntity) {
        return new PatientDTO(
                patientEntity.getId(),
                patientEntity.getName(),
                patientEntity.getEmail(),
                patientEntity.getDocument(),
                patientEntity.getPhone(),
                patientEntity.getActive(),
                patientEntity.getBirthDate(),
                patientEntity.getAge(),
                patientEntity.getAddress()
        );
    }

    public DataPatientDTO convertPatientEntityToDataPatientDTO(PatientEntity patientEntity) {
        return new DataPatientDTO(
                patientEntity.getName(),
                patientEntity.getEmail(),
                patientEntity.getDocument(),
                patientEntity.getPhone(),
                patientEntity.getAge()
        );
    }

    public Integer calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            return 0;
        }
        LocalDate today = LocalDate.now();
        return Period.between(today, birthDate).getYears();
    }
}
