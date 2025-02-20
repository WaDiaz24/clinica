package com.api.clinica.domain.data.repositories;

import com.api.clinica.domain.data.entities.ConsultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface IConsultRepository extends JpaRepository<ConsultEntity, Long> {
    Boolean existsByPatient_IdAndConsultDateBetween(Long id, LocalDateTime firstSchedule, LocalDateTime lastSchedule);
    Boolean existsByMedico_IdAndConsultDate(Long id, LocalDateTime date);
}
