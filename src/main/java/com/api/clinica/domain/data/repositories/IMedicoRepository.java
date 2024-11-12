package com.api.clinica.domain.data.repositories;

import com.api.clinica.domain.data.entities.MedicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicoRepository extends JpaRepository<MedicoEntity, Long> {

    Page<MedicoEntity> findByActiveTrue(Pageable pageable);
}
