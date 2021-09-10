package com.caito.controlgastos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewCreditCard {

    private Long instituion_id;
    private Long card_id;
    private Long user_id;

}
