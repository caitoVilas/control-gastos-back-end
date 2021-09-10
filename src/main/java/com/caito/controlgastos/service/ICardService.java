package com.caito.controlgastos.service;

import com.caito.controlgastos.dto.CardResponse;
import com.caito.controlgastos.dto.NewCard;
import javassist.NotFoundException;

import java.util.List;

public interface ICardService {

    public CardResponse createCard(NewCard newCard);
    public CardResponse getCard(Long id) throws NotFoundException;
    public List<CardResponse> getAllCards();
    public void deleteCard(Long id) throws NotFoundException;
}
