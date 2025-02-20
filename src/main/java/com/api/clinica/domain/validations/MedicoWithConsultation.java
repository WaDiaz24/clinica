package com.api.clinica.domain.validations;

import com.api.clinica.domain.data.repositories.IConsultRepository;
import com.api.clinica.domain.dto.DataScheduleConsult;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MedicoWithConsultation implements QueryValidator{

    private final IConsultRepository consultRepository;

    public void validate(DataScheduleConsult data){

        if(data.idMedico() == null){
            return;
        }

        var medicoWithConsultation = consultRepository.existsByMedico_IdAndConsultDate(data.idMedico(), data.consultDate());

        if(medicoWithConsultation){
            throw new ValidationException("Medico with consultation already exists");
        }
    }
}
