package com.api.clinica.domain.data.repositories;

import com.api.clinica.domain.data.entities.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<PatientEntity, Long> {
    Page<PatientEntity> findByActiveTrue(Pageable pageable);
}