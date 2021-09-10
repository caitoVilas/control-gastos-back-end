package com.caito.controlgastos.service;

import com.caito.controlgastos.dto.CardResponse;
import com.caito.controlgastos.dto.NewCard;
import com.caito.controlgastos.entity.Card;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICardService {

    public CardResponse createCard(NewCard newCard);
    public CardResponse getCard(Long id) throws NotFoundException;
    public List<Card> getAllCards();
    public Page<Card> getAllCardsPage(Pageable pageable);
    public void deleteCard(Long id) throws NotFoundException;
}
