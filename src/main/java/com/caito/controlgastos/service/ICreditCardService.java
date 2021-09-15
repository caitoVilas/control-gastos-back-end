package com.caito.controlgastos.service;

import com.caito.controlgastos.dto.CreditCardResponse;
import com.caito.controlgastos.dto.NewCreditCard;
import com.caito.controlgastos.entity.CreditCard;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICreditCardService {

    public CreditCard createCreditCard(NewCreditCard newCreditCard) throws NotFoundException;
    public CreditCardResponse getCreditCard(Long id) throws NotFoundException;
    public List<CreditCardResponse> getAllCreditsCard();
    public void deleteCreditCard(Long id) throws NotFoundException;
}
