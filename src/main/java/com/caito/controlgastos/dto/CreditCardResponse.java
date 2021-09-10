package com.caito.controlgastos.dto;

import com.caito.controlgastos.entity.Card;
import com.caito.controlgastos.entity.Institution;

import java.time.LocalDateTime;

public class CreditCardResponse {

    private Long id;
    private Institution institution;
    private Card card;
    private LocalDateTime created;
    private LocalDateTime updated;
}
