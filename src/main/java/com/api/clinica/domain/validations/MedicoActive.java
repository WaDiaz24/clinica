package com.api.clinica.domain.validations;

import com.api.clinica.domain.data.repositories.IMedicoRepository;
import com.api.clinica.domain.dto.DataScheduleConsult;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MedicoActive implements QueryValidator{

    private final IMedicoRepository medicoRepository;

    public void validate(DataScheduleConsult data){
        if(data.idMedico() == null){
            return;
        }

        Boolean medicoActive = medicoRepository.findActiveById(data.idMedico());

        if(!medicoActive){
            throw new ValidationException("No se permite programar citas con médicos inactivos en el sistema");
        }
    }
}
