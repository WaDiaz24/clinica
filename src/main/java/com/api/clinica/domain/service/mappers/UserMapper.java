package com.api.clinica.domain.service.mappers;

import com.api.clinica.domain.data.entities.UserEntity;
import com.api.clinica.domain.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity convertUserDtoToUserEntity(UserDTO user){
        return new UserEntity(
                user.id(),
                user.name(),
                user.username(),
                user.email(),
                user.password()
        );
    }

    public UserDTO convertUserEntityToUserDTO(UserEntity user){
        return new UserDTO(
                user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getPassword()
        );
    }
}
