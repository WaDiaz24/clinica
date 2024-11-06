package com.api.clinica.controller;

import com.api.clinica.domain.dto.MedicoDTO;
import com.api.clinica.domain.service.IMedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/v1/api/clinica")
public class MedicoController {

    private final IMedicoService medicoService;

    @PostMapping("/medico")
    public ResponseEntity<MedicoDTO> saveMedico(@RequestBody MedicoDTO medicoDTO, UriComponentsBuilder uriComponentsBuilder) {
        MedicoDTO savedMedico = medicoService.save(medicoDTO);
        var url = uriComponentsBuilder.path("/v1/api/clinica/medico/{id}").buildAndExpand(savedMedico.id()).toUri();
        return ResponseEntity.created(url).body(savedMedico);
    }

    @GetMapping("/medicos")
    public ResponseEntity<List<MedicoDTO>> getAllMedicos() {
        return ResponseEntity.ok(medicoService.findAll());
    }

    @GetMapping("/medico/{id}")
    public ResponseEntity<MedicoDTO> getMedicoById(@PathVariable Long id) {
        return ResponseEntity.of(medicoService.findMedicoById(id));
    }

    @DeleteMapping("/medico/{id}")
    public ResponseEntity<Boolean> deleteMedico(@PathVariable Long id) {
        return ResponseEntity.of(medicoService.delete(id));
    }

}
