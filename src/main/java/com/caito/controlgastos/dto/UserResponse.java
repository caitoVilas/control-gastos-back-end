package com.caito.controlgastos.dto;

import com.caito.controlgastos.entity.Role;

import java.util.Set;

public class UserResponse {

    private Long id;
    private String userName;
    private String email;
    private Set<Role> roles;
}
