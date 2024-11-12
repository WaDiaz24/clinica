package com.api.clinica.domain.service;

import com.api.clinica.domain.dto.DataMedicoDTO;
import com.api.clinica.domain.dto.MedicoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IMedicoService {
    public MedicoDTO save(MedicoDTO medicoDTO);
    public Page<DataMedicoDTO> findAll(Pageable pageable);
    public Optional<DataMedicoDTO> findMedicoById(Long id);
    public MedicoDTO update(Long id, MedicoDTO medico);
    public Optional<Boolean> delete(Long id);
}
