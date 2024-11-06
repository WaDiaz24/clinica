package com.api.clinica.domain.service;

import com.api.clinica.domain.dto.MedicoDTO;

import java.util.List;
import java.util.Optional;

public interface IMedicoService {
    public MedicoDTO save(MedicoDTO medicoDTO);
    public List<MedicoDTO> findAll();
    public Optional<MedicoDTO> findMedicoById(Long id);
    public MedicoDTO update(Long id);
    public Optional<Boolean> delete(Long id);
}
