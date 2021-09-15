package com.caito.controlgastos.service.impl;

import com.caito.controlgastos.constants.ConstantsExceptionMessages;
import com.caito.controlgastos.dto.*;
import com.caito.controlgastos.entity.Card;
import com.caito.controlgastos.entity.CreditCard;
import com.caito.controlgastos.entity.Institution;
import com.caito.controlgastos.entity.User;
import com.caito.controlgastos.exceptions.customs.BadRequestException;
import com.caito.controlgastos.repository.CardRepository;
import com.caito.controlgastos.repository.CreditCardRepository;
import com.caito.controlgastos.repository.InstitutionRepository;
import com.caito.controlgastos.repository.UserRepository;
import com.caito.controlgastos.service.ICreditCardService;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardService implements ICreditCardService {

    @Autowired
    private CreditCardRepository repository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private UserRepository userRepository;



    @Override
    public CreditCard createCreditCard(NewCreditCard newCreditCard) throws NotFoundException {

        if (newCreditCard.getCard_id() == null) {
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CC_NOT_CARD_ID);
        }
        if (newCreditCard.getInstitution_id() == null) {
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CC_NOT_INST_ID);
        }
        if (newCreditCard.getUser_id() == null) {
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CC_NOT_USEER_ID);
        }




        User user = userRepository.findById(newCreditCard.getUser_id()).orElseThrow(() -> new NotFoundException(
                ConstantsExceptionMessages.MSG_CC_NOT_FOUD));
        Institution institution = institutionRepository.findById(newCreditCard.getInstitution_id()).orElseThrow(() ->
                new NotFoundException(ConstantsExceptionMessages.MSG_INST_NOT_FOUND));
        Card card = cardRepository.findById(newCreditCard.getCard_id()).orElseThrow(() -> new NotFoundException(
                ConstantsExceptionMessages.MSG_CARD_NOT_FOUND));

        if (repository.existsByUserAndInstitutionAndCard(user, institution, card)) {
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CC_EXISTS);
        }
        CreditCard creditCard = new CreditCard();
        creditCard.setUser(user);
        creditCard.setInstitution(institution);
        creditCard.setCard(card);

        repository.save(creditCard);
        return creditCard;



        /*CreditCardResponse response = new CreditCardResponse(creditCard.getId(),
                                                             user,
                                                             institution,
                                                             card,
                                                             creditCard.getCreation());

        return response;
        ModelMapper mapper = new ModelMapper();
        repository.save(mapper.map(newCreditCard, CreditCard.class));
        return mapper.map(newCreditCard, CreditCard.class);*/

    }


    @Override
    public CreditCardResponse getCreditCard(Long id) throws NotFoundException {

        if (id == null){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CC_NOT_CARD_ID);
        }

        CreditCard creditCard = repository.findById(id).orElseThrow(() -> new NotFoundException(
                ConstantsExceptionMessages.MSG_CC_NOT_FOUD));


        ModelMapper mapper = new ModelMapper();
        return mapper.map(creditCard, CreditCardResponse.class);
    }

    @Override
    public List<CreditCardResponse> getAllCreditsCard() {

        List<CreditCardResponse> cards = repository.findAll()
                .stream()
                .map(this::creditCardToDto)
                .collect(Collectors.toList());
        return cards;
    }

    @Override
    public void deleteCreditCard(Long id) throws NotFoundException {

        if (id == null){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CC_NOT_CARD_ID);
        }

        CreditCard creditCard = repository.findById(id).orElseThrow(() -> new NotFoundException(
                ConstantsExceptionMessages.MSG_CC_NOT_FOUD ));

        repository.deleteById(id);
    }

    private CardResponse getCard(Long id) throws NotFoundException {

        Card card = cardRepository.findById(id).orElseThrow(() -> new NotFoundException(
                ConstantsExceptionMessages.MSG_CARD_NOT_FOUND ));
        ModelMapper mapper = new ModelMapper();
        return mapper.map(card, CardResponse.class);
    }

    private InstitutionResponse getInstitution(Long id) throws NotFoundException {

        Institution institution = institutionRepository.findById(id).orElseThrow(() ->
                new NotFoundException(ConstantsExceptionMessages.MSG_INST_NOT_FOUND));
        ModelMapper mapper = new ModelMapper();
        return mapper.map(institution, InstitutionResponse.class);
    }

    private UserResponse getUser(Long id) throws NotFoundException {

        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(
                ConstantsExceptionMessages.MSG_USER_NOT_FOUND ));
        ModelMapper mapper = new ModelMapper();
        return mapper.map(user, UserResponse.class);
    }

    private CreditCardResponse creditCardToDto(CreditCard creditCard){

        ModelMapper mapper = new ModelMapper();
        CreditCardResponse response = mapper.map(creditCard, CreditCardResponse.class);
        return response;
    }
}
