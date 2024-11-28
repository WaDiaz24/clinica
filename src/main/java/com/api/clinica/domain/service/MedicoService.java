package com.api.clinica.domain.service;

import com.api.clinica.domain.data.entities.AddressEntity;
import com.api.clinica.domain.data.entities.MedicoEntity;
import com.api.clinica.domain.data.repositories.IMedicoRepository;
import com.api.clinica.domain.dto.DataMedicoDTO;
import com.api.clinica.domain.dto.MedicoDTO;
import com.api.clinica.domain.service.mappers.MedicoMapper;
import com.api.clinica.infra.errors.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MedicoService implements IMedicoService {

    private final IMedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;

    @Override
    public MedicoDTO save(MedicoDTO medicoDTO) {
        MedicoEntity medico = medicoMapper.convertMedicoDTOToMedicoEntity(medicoDTO);
        MedicoEntity savedMedico = medicoRepository.save(medico);
        return medicoMapper.convertMedicoEntityToMedicoDTO(savedMedico);
    }

    @Override
    public Page<DataMedicoDTO> findAll(Pageable pageable) {
        return medicoRepository.findByActiveTrue(pageable)
                .map(medicoMapper::convertMedicoEntityToDataMedicoDTO);
    }

    @Override
    public Optional<DataMedicoDTO> findMedicoById(Long id) {
        return medicoRepository.findByIdAndActiveTrue(id).map(medicoMapper::convertMedicoEntityToDataMedicoDTO);
    }

    @Override
    public MedicoDTO update(Long id, MedicoDTO medico) {
        MedicoEntity medicoE = medicoRepository.findByActiveTrueAndId(id);
        if (medicoE == null) {
            throw new ResourceNotFoundException("Medico not found with id: " + id);
        }

        medicoE.setName(medico.name());
        medicoE.setEmail(medico.email());
        medicoE.setPhone(medico.phone());
        medicoE.setDocument(medico.document());

        AddressEntity addressE = medicoE.getAddress();
        addressE.setStreet(medico.address().getStreet());
        addressE.setDistrict(medico.address().getDistrict());
        addressE.setCity(medico.address().getCity());
        addressE.setNumber(medico.address().getNumber());
        addressE.setComplement(medico.address().getComplement());

        medicoE.setAddress(addressE);

        medicoRepository.save(medicoE);

        return medicoMapper.convertMedicoEntityToMedicoDTO(medicoE);
    }

    @Override
    public Optional<Boolean> delete(Long id) {
        return medicoRepository.findById(id)
                .filter(MedicoEntity::getActive)
                .map(medico -> {
                    medico.setActive(false);
                    medicoRepository.save(medico);
                    return true;
                });
    }
}
