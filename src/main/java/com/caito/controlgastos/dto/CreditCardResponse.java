package com.caito.controlgastos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditCardResponse {

    private Long id;
    private UserResponse user;
    private InstitutionResponse institution;
    private CardResponse card;
    private LocalDateTime created;
}
