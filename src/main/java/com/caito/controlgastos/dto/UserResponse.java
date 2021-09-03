package com.caito.controlgastos.dto;

import com.caito.controlgastos.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

    private Long id;
    private String userName;
    private String email;
    private Set<Role> roles;
}
