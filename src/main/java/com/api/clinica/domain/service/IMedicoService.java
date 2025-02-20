package com.api.clinica.domain.service;

import com.api.clinica.domain.dto.DataMedicoDTO;
import com.api.clinica.domain.dto.MedicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IMedicoService {
    MedicoDTO save(MedicoDTO medicoDTO);
    Page<DataMedicoDTO> findAll(Pageable pageable);
    Optional<DataMedicoDTO> findMedicoById(Long id);
    MedicoDTO update(Long id, MedicoDTO medico);
    Optional<Boolean> delete(Long id);
}
