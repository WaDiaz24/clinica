package com.api.clinica.domain.service;

import com.api.clinica.domain.data.entities.MedicoEntity;
import com.api.clinica.domain.data.repositories.IMedicoRepository;
import com.api.clinica.domain.dto.MedicoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MedicoService implements IMedicoService{

    private final IMedicoRepository medicoRepository;

    @Override
    public MedicoDTO save(MedicoDTO medicoDTO) {
        MedicoEntity medico = convertMedicoDTOToMedicoEntity(medicoDTO);
        MedicoEntity savedMedico = medicoRepository.save(medico);
        return convertMedicoEntityToMedicoDTO(savedMedico);
    }

    @Override
    public List<MedicoDTO> findAll() {
        return medicoRepository.findAll().stream()
                .map(this::convertMedicoEntityToMedicoDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<MedicoDTO> findMedicoById(Long id) {
        return medicoRepository.findById(id).map(this::convertMedicoEntityToMedicoDTO);
    }

    @Override
    public MedicoDTO update(Long id) {
        return null;
    }

    @Override
    public Optional<Boolean> delete(Long id) {
        return medicoRepository.findById(id)
                .map(medico -> {
                    medico.setActive(false);
                    medicoRepository.save(medico);
                    return true;
                });
    }

    public MedicoEntity convertMedicoDTOToMedicoEntity(MedicoDTO medicoDTO) {
        return new MedicoEntity(
                medicoDTO.id(),
                medicoDTO.name(),
                medicoDTO.email(),
                medicoDTO.phone(),
                medicoDTO.document(),
                medicoDTO.active() != null ? medicoDTO.active() : true,
                medicoDTO.speciality(),
                medicoDTO.address()
        );
    }

    public MedicoDTO convertMedicoEntityToMedicoDTO(MedicoEntity medicoEntity) {
        return new MedicoDTO(
                medicoEntity.getId(),
                medicoEntity.getName(),
                medicoEntity.getEmail(),
                medicoEntity.getPhone(),
                medicoEntity.getDocument(),
                medicoEntity.getActive(),
                medicoEntity.getSpecialty(),
                medicoEntity.getAddress()
        );
    }
}
