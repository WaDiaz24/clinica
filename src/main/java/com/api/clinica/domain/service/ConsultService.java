package com.api.clinica.domain.service;

import com.api.clinica.domain.data.entities.ConsultEntity;
import com.api.clinica.domain.data.entities.MedicoEntity;
import com.api.clinica.domain.data.repositories.IConsultRepository;
import com.api.clinica.domain.data.repositories.IMedicoRepository;
import com.api.clinica.domain.data.repositories.IPatientRepository;
import com.api.clinica.domain.dto.DataCancelConsult;
import com.api.clinica.domain.dto.DetailsCancelConsult;
import com.api.clinica.domain.dto.DataDetailsConsult;
import com.api.clinica.domain.dto.DataScheduleConsult;
import com.api.clinica.domain.validations.QueryValidator;
import com.api.clinica.infra.errors.IntegrityValidation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultService {

    private final IConsultRepository consultRepository;
    private final IMedicoRepository medicoRepository;
    private final IPatientRepository patientRepository;
    private final List<QueryValidator> validators;

    @Transactional
    public DataDetailsConsult scheduleConsult(DataScheduleConsult dataConsult) {
        if(!patientRepository.findById(dataConsult.idPatient()).isPresent()) {
            throw new IntegrityValidation("Id patient not found");
        }
        if(dataConsult.idMedico() != null && !medicoRepository.existsById(dataConsult.idMedico())) {
            throw new IntegrityValidation("Id medico not found");
        }

        validators.forEach(validator -> validator.validate(dataConsult));

        var medico = selectMedico(dataConsult);

        if(medico == null) {
            throw new IntegrityValidation("Doctor with specialty not available for this time");
        }

        var patient = patientRepository.findById(dataConsult.idPatient()).get();

        var consult = new ConsultEntity(null, patient, medico, dataConsult.consultDate(), null);
        consultRepository.save(consult);

        return new DataDetailsConsult(consult);
    }

    @Transactional
    public DetailsCancelConsult cancelConsult(DataCancelConsult dataCancelConsult){
        var consult = consultRepository.findById(dataCancelConsult.id())
                        .orElseThrow(() -> new IntegrityValidation("Consultation not found"));

        var now = LocalDateTime.now();
        if(now.isAfter(consult.getConsultDate().minusHours(24))) {
            throw new IntegrityValidation("Una cita solo se puede cancelar con al menos 24 horas de anticipación.");
        }

        consultRepository.deleteById(dataCancelConsult.id());

        return new DetailsCancelConsult(consult.getPatient().getName(), consult.getMedico().getName(), dataCancelConsult.cancellationMotive().name());
    }

    private MedicoEntity selectMedico(DataScheduleConsult dataConsult) {
        if(dataConsult.idMedico() != null) {
            return medicoRepository.getReferenceById(dataConsult.idMedico());
        }
        if (dataConsult.specialty() == null){
            throw new IntegrityValidation("Speciality not found");
        }

        return medicoRepository.selectMedicoWithSpecialtyOnDate(dataConsult.specialty(), dataConsult.consultDate());
    }
}
