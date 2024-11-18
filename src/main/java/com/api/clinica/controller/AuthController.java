package com.api.clinica.controller;

import com.api.clinica.domain.data.entities.UserEntity;
import com.api.clinica.domain.dto.AuthDTO;
import com.api.clinica.infra.security.DataJwtToken;
import com.api.clinica.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clinica")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<DataJwtToken> login(@RequestBody @Valid AuthDTO authDTO) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                authDTO.username(), authDTO.password()
        );
        var userAuth = authenticationManager.authenticate(authToken);
        var token = tokenService.generateToken((UserEntity) userAuth.getPrincipal());
        return ResponseEntity.ok(new DataJwtToken(token));
    }
}
