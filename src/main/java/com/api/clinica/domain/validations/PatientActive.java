package com.api.clinica.domain.validations;

import com.api.clinica.domain.data.repositories.IPatientRepository;
import com.api.clinica.domain.dto.DataScheduleConsult;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PatientActive implements QueryValidator{

    private final IPatientRepository patientRepository;

    public void validate(DataScheduleConsult data){
        if (data.idPatient() == null){
            return;
        }

        Boolean patientActive = patientRepository.findActiveById(data.idPatient());
        if (!patientActive){
            throw new ValidationException("No se permite agendar citas con pacientes inactivos en el sistema");
        }
    }
}
