package com.api.clinica.domain.data.repositories;

import com.api.clinica.domain.data.entities.PatientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IPatientRepository extends JpaRepository<PatientEntity, Long> {
    Page<PatientEntity> findByActiveTrue(Pageable pageable);
    Optional<PatientEntity> findByActiveTrueAndId(Long id);
    @Query("""
            select p.active from PatientEntity p
            where p.id=:idPatient
            """)
    Boolean findActiveById(Long idPatient);
}
