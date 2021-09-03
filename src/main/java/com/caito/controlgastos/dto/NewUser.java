package com.caito.controlgastos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewUser {

    private String userName;
    private String email;
    private String password;
    private String roles;
}
