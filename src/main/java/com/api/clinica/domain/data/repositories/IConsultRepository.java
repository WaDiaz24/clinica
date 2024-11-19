package com.api.clinica.domain.data.repositories;

import com.api.clinica.domain.data.entities.ConsultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultRepository extends JpaRepository<ConsultEntity, Long> {
}
