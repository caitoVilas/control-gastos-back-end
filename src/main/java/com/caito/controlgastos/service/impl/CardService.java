package com.caito.controlgastos.service.impl;

import com.caito.controlgastos.constants.ConstantsExceptionMessages;
import com.caito.controlgastos.dto.CardResponse;
import com.caito.controlgastos.dto.NewCard;
import com.caito.controlgastos.entity.Card;
import com.caito.controlgastos.exceptions.customs.BadRequestException;
import com.caito.controlgastos.repository.CardRepository;
import com.caito.controlgastos.service.ICardService;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CardService implements ICardService {

    @Autowired
    private CardRepository repository;


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
    public List<CardResponse> getAllCards() {

        List<CardResponse> cards = repository.findAll()
                .stream()
                .map(this::cardToDto)
                .collect(Collectors.toList());
        return cards;
    }

    @Override
    public void deleteCard(Long id) throws NotFoundException {

        if (id == null){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CARD_NOT_ID);
        }

        Card card = repository.findById(id).orElseThrow(()-> new NotFoundException(
                ConstantsExceptionMessages.MSG_CARD_NOT_FOUND));

        repository.deleteById(id);

    }

    private CardResponse cardToDto(Card card){

        ModelMapper mapper = new ModelMapper();
        CardResponse cardResponse = mapper.map(card, CardResponse.class);
        return cardResponse;
    }
}
