package com.api.clinica.domain.service;

import com.api.clinica.domain.data.entities.AddressEntity;
import com.api.clinica.domain.data.entities.PatientEntity;
import com.api.clinica.domain.data.repositories.IPatientRepository;
import com.api.clinica.domain.dto.DataPatientDTO;
import com.api.clinica.domain.dto.PatientDTO;
import com.api.clinica.infra.errors.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PatientService implements IPatientService{

    private final IPatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientDTO save(PatientDTO patient) {
        PatientEntity patientE = patientMapper.convertPatientDTOToPatientEntity(patient);
        Integer age = calculateAge(patientE.getBirthDate());
        patientE.setAge(age);
        return patientMapper.convertPatientEntityToPatientDTO(patientRepository.save(patientE));
    }

    @Override
    public Page<DataPatientDTO> findAll(Pageable pageable) {
        return patientRepository.findByActiveTrue(pageable).map(patientMapper::convertPatientEntityToDataPatientDTO);
    }

    @Override
    public Optional<DataPatientDTO> findPatientById(Long id) {
        return patientRepository.findByActiveTrueAndId(id).map(patientMapper::convertPatientEntityToDataPatientDTO);
    }

    @Override
    public PatientDTO update(Long id, PatientDTO patient) {
        PatientEntity patientE = patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: "+id));
        patientE.setName(patient.name());
        patientE.setEmail(patient.email());
        patientE.setDocument(patient.document());
        patientE.setPhone(patient.phone());
        patientE.setBirthDate(patient.birthDate());
        patientE.setAge(calculateAge(patientE.getBirthDate()));

        AddressEntity address = patientE.getAddress();
        address.setStreet(patient.address().getStreet());
        address.setDistrict(patient.address().getDistrict());
        address.setCity(patient.address().getCity());
        address.setNumber(patient.address().getNumber());
        address.setComplement(patient.address().getComplement());

        patientE.setAddress(address);

        return patientMapper.convertPatientEntityToPatientDTO(patientRepository.save(patientE));
    }

    @Override
    public Optional<Boolean> delete(Long id) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setActive(false);
                    patientRepository.save(patient);
                    return true;
                });
    }

    public Integer calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            return 0;
        }
        LocalDate today = LocalDate.now();
        return Period.between(birthDate, today).getYears();
    }
}
