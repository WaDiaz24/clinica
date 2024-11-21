package com.api.clinica.domain.validations;

import com.api.clinica.domain.dto.DataScheduleConsult;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ClinicOperatingHours implements QueryValidator{

    public void validate(DataScheduleConsult data){
        var sunday = DayOfWeek.SUNDAY.equals(data.consultDate().getDayOfWeek());
        var beforeOpening = data.consultDate().getHour()<7;
        var afterClosing = data.consultDate().getHour()>18;

        if (sunday || beforeOpening || afterClosing){
            throw new ValidationException("El horario de atención de la clínica es de lunes a sábado, de 07:00 a 19:00 horas");
        }
    }
}
