package com.caito.controlgastos.service;

import com.caito.controlgastos.dto.CreditCardResponse;
import com.caito.controlgastos.dto.NewCreditCard;
import com.caito.controlgastos.entity.CreditCard;
import javassist.NotFoundException;

import java.util.List;

public interface ICreditCardService {

    public CreditCardResponse createCreditCard(NewCreditCard newCreditCard) throws NotFoundException;
    public CreditCardResponse getCreditCard(Long id) throws NotFoundException;
    public List<CreditCard> getAllCreditCards();
    public void deleteCreditCard(Long id) throws NotFoundException;
}
