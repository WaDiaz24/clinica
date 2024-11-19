package com.api.clinica.domain.data.repositories;

import com.api.clinica.domain.data.entities.MedicoEntity;
import com.api.clinica.domain.data.entities.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IMedicoRepository extends JpaRepository<MedicoEntity, Long> {

    Page<MedicoEntity> findByActiveTrue(Pageable pageable);
    Optional<MedicoEntity> findByIdAndActiveTrue(Long id);

    @Query("""
           select m from MedicoEntity m
           where m.active = true 
           and m.specialty=:specialty and
           m.id not in (
           select c.medico.id from ConsultEntity c
           where c.consultDate=:date
           )
           order by rand()
           limit 1
           """)
    MedicoEntity selectMedicoWithSpecialtyOnDate(Specialty specialty, LocalDateTime date);
}
