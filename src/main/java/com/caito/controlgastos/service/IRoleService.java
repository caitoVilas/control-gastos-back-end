package com.caito.controlgastos.service;

import com.caito.controlgastos.entity.Role;
import com.caito.controlgastos.enums.RoleName;

import java.util.Optional;

public interface IRoleService {

    public Role saveRole(Role role);
    public Optional<Role> getRole(RoleName roleName);
}
