package com.api.clinica.domain.service;

import com.api.clinica.domain.data.entities.ConsultEntity;
import com.api.clinica.domain.data.entities.MedicoEntity;
import com.api.clinica.domain.data.repositories.IConsultRepository;
import com.api.clinica.domain.data.repositories.IMedicoRepository;
import com.api.clinica.domain.data.repositories.IPatientRepository;
import com.api.clinica.domain.dto.DataScheduleConsult;
import com.api.clinica.infra.errors.IntegrityValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ConsultService {

    private final IConsultRepository consultRepository;
    private final IMedicoRepository medicoRepository;
    private final IPatientRepository patientRepository;

    public void scheduleConsult(DataScheduleConsult dataConsult) {
        if(patientRepository.findById(dataConsult.idPatient()).isPresent()) {
            throw new IntegrityValidation("Id patient not found");
        }
        if(dataConsult.idMedico() != null && medicoRepository.existsById(dataConsult.idMedico())) {
            throw new IntegrityValidation("Id medico not found");
        }

        var medico = selectMedico(dataConsult);
        var patient = patientRepository.findById(dataConsult.idPatient()).get();

        var consult = new ConsultEntity(null, patient, medico, dataConsult.consultDate());
        consultRepository.save(consult);
    }

    private MedicoEntity selectMedico(DataScheduleConsult dataConsult) {
        if(dataConsult.idMedico() != null) {
            return medicoRepository.getReferenceById(dataConsult.idMedico());
        }
        if (dataConsult.specialty() == null){
            throw new IntegrityValidation("Especialidade not found");
        }

        return medicoRepository.selectMedicoWithSpecialtyOnDate(dataConsult.specialty(), dataConsult.consultDate());
    }
}
