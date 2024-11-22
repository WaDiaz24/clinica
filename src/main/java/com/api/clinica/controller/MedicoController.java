package com.api.clinica.controller;

import com.api.clinica.domain.dto.DataMedicoDTO;
import com.api.clinica.domain.dto.MedicoDTO;
import com.api.clinica.domain.service.IMedicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@RestController()
@RequestMapping("/v1/api/clinica")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    private final IMedicoService medicoService;

    @PostMapping("/medico")
    public ResponseEntity<MedicoDTO> saveMedico(@RequestBody @Valid MedicoDTO medicoDTO, UriComponentsBuilder uriComponentsBuilder) {
        MedicoDTO savedMedico = medicoService.save(medicoDTO);
        var url = uriComponentsBuilder.path("/v1/api/clinica/medico/{id}").buildAndExpand(savedMedico.id()).toUri();
        return ResponseEntity.created(url).body(savedMedico);
    }

    @GetMapping("/medicos")
    public ResponseEntity<Page<DataMedicoDTO>> getAllMedicos(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(medicoService.findAll(pageable));
    }

    @GetMapping("/medico/{id}")
    public ResponseEntity<DataMedicoDTO> getMedicoById(@PathVariable Long id) {
        return ResponseEntity.of(medicoService.findMedicoById(id));
    }

    @PutMapping("/medico/{id}")
    public ResponseEntity<MedicoDTO> updateMedico(@PathVariable Long id, @RequestBody @Valid MedicoDTO medicoDTO) {
        return ResponseEntity.ok(medicoService.update(id, medicoDTO));
    }

    @DeleteMapping("/medico/{id}")
    public ResponseEntity<Boolean> deleteMedico(@PathVariable Long id) {
        return ResponseEntity.of(medicoService.delete(id));
    }

}
