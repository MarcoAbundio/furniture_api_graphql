package com.api.furniture.repository;

import java.util.Optional;
import com.api.furniture.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Optional<Role> findByAuthority(String email);
}
