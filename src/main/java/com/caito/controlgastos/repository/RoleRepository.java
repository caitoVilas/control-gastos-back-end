package com.caito.controlgastos.repository;

import com.caito.controlgastos.entity.Role;
import com.caito.controlgastos.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Optional<Role> findByRoleName(RoleName roleName);
}
