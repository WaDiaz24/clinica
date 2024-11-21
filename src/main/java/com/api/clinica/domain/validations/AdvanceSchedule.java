package com.api.clinica.domain.validations;

import com.api.clinica.domain.dto.DataScheduleConsult;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceSchedule implements QueryValidator{

    public void validate(DataScheduleConsult data){
        var now = LocalDateTime.now();
        var consultationTime = data.consultDate();

        var thirtyMinuteDifference = Duration.between(now, consultationTime).toMinutes()<30;

        if(thirtyMinuteDifference){
            throw new ValidationException("Las consultas deben programarse con al menos 30 minutos de anticipación");
        }
    }
}
