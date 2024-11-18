package com.api.clinica.controller;

import com.api.clinica.domain.dto.UserDTO;
import com.api.clinica.domain.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clinica")
public class UserController {

    public final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid UserDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
}
