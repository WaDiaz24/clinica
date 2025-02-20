package com.api.clinica.domain.service;

import com.api.clinica.domain.data.entities.UserEntity;
import com.api.clinica.domain.data.repositories.IUserRepository;
import com.api.clinica.domain.dto.UserDTO;
import com.api.clinica.domain.service.mappers.UserMapper;
import com.api.clinica.infra.errors.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO save(UserDTO user) {
        UserEntity userEntity = userMapper.convertUserDtoToUserEntity(user);
        if(userRepository.existsByUsername(userEntity.getUsername())) {
            throw new ResourceNotFoundException("Username ya exist");
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userMapper.convertUserEntityToUserDTO(userRepository.save(userEntity));
    }
}
