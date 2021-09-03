package com.caito.controlgastos.service.impl;

import com.caito.controlgastos.entity.Role;
import com.caito.controlgastos.enums.RoleName;
import com.caito.controlgastos.repository.RoleRepository;
import com.caito.controlgastos.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> getRole(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
