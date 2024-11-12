package com.api.clinica.domain.service;

import com.api.clinica.domain.data.entities.MedicoEntity;
import com.api.clinica.domain.dto.DataMedicoDTO;
import com.api.clinica.domain.dto.MedicoDTO;
import org.springframework.stereotype.Service;

@Service
public class MedicoMapper {

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

    public DataMedicoDTO convertMedicoEntityToDataMedicoDTO(MedicoEntity medicoEntity) {
        return new DataMedicoDTO(
                medicoEntity.getName(),
                medicoEntity.getEmail(),
                medicoEntity.getPhone(),
                medicoEntity.getDocument(),
                medicoEntity.getSpecialty()
        );
    }
}
