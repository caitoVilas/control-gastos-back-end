package com.caito.controlgastos.service.impl;

import com.caito.controlgastos.constants.ConstantsExceptionMessages;
import com.caito.controlgastos.dto.CardResponse;
import com.caito.controlgastos.dto.NewCard;
import com.caito.controlgastos.entity.Card;
import com.caito.controlgastos.exceptions.customs.BadRequestException;
import com.caito.controlgastos.repository.CardRepository;
import com.caito.controlgastos.repository.CreditCardRepository;
import com.caito.controlgastos.service.ICardService;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService implements ICardService {

    @Autowired
    private CardRepository repository;
    @Autowired
    private CreditCardRepository creditCardRepository;


    @Override
    public CardResponse createCard(NewCard newCard) {
        if (repository.existsByName(newCard.getName())){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CARD_EXISTS_BY_NAME);
        }

        if (newCard.getName() == null | newCard.getName() == ""){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CARD_NAME_EMPTY);
        }
        ModelMapper mapper = new ModelMapper();
        Card card = mapper.map(newCard, Card.class);
        repository.save(card);
        return mapper.map(card, CardResponse.class);
    }

    @Override
    public CardResponse getCard(Long id) throws NotFoundException {

        if (id == null){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CARD_NOT_ID);
        }

        Card card = repository.findById(id).orElseThrow(() -> new NotFoundException(
                ConstantsExceptionMessages.MSG_CARD_NOT_FOUND));
        ModelMapper mapper = new ModelMapper();
        return mapper.map(card, CardResponse.class);
    }

    @Override
    public List<Card> getAllCards() {
        return repository.findAll();
    }

    @Override
    public Page<Card> getAllCardsPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void deleteCard(Long id) throws NotFoundException {
        if (id == null){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CARD_NOT_ID);
        }

        Card card = repository.findById(id).orElseThrow(()-> new NotFoundException(
                ConstantsExceptionMessages.MSG_CARD_NOT_FOUND));
        if (creditCardRepository.existsByCard(card)){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CARD_IN_USE);
        }
        repository.deleteById(id);
    }
}
