package com.example.marketplace.repository;

import com.example.marketplace.entities.Role;
import com.example.marketplace.enumerations.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
