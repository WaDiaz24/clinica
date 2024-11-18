package com.api.clinica.domain.data.repositories;

import com.api.clinica.domain.data.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    UserDetails findByUsername(String username);
    Boolean existsByUsername(String username);
}
