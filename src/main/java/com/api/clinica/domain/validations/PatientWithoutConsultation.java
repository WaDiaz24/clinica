package com.api.clinica.domain.validations;

import com.api.clinica.domain.data.repositories.IConsultRepository;
import com.api.clinica.domain.dto.DataScheduleConsult;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PatientWithoutConsultation implements QueryValidator{

    private final IConsultRepository consultRepository;

    public void validate(DataScheduleConsult data) {
        var firstSchedule = data.consultDate().withHour(7);
        var lastSchedule = data.consultDate().withHour(18);

        var patientWithConsultation = consultRepository.existsByPatient_IdAndConsultDateBetween(data.idPatient(), firstSchedule, lastSchedule);

        if(patientWithConsultation){
            throw new ValidationException("The Patient already has a consultation for that day");
        }
    }
}
