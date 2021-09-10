package com.caito.controlgastos.service.impl;

import com.caito.controlgastos.constants.ConstantsExceptionMessages;
import com.caito.controlgastos.dto.CreditCardResponse;
import com.caito.controlgastos.dto.NewCreditCard;
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

@Service
public class CreditCardService implements ICreditCardService {

    @Autowired
    private CreditCardRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private CardRepository cardRepository;


    @Override
    public CreditCardResponse createCreditCard(NewCreditCard newCreditCard) throws NotFoundException {

        if (newCreditCard.getCard_id() == null){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CC_NOT_CARD_ID);
        }
        if (newCreditCard.getInstituion_id() == null){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CC_NOT_INST_ID);
        }
        if (newCreditCard.getUser_id() == null){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CC_NOT_USEER_ID);
        }

        User user = userRepository.findById(newCreditCard.getUser_id()).orElseThrow(()-> new NotFoundException(
                ConstantsExceptionMessages.MSG_USER_NOT_FOUND));
        Institution institution = institutionRepository.findById(newCreditCard.getInstituion_id()).orElseThrow(() ->
                new NotFoundException(ConstantsExceptionMessages.MSG_INST_NOT_FOUND));
        Card card = cardRepository.findById(newCreditCard.getCard_id()).orElseThrow(() -> new
                NotFoundException(ConstantsExceptionMessages.MSG_CARD_NOT_FOUND));

        if(repository.existsByUserAndInstitutionAndCard(user, institution, card)){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CC_EXISTS);
        }

        ModelMapper mapper = new ModelMapper();
        CreditCard creditCard = mapper.map(newCreditCard, CreditCard.class);
        repository.save(creditCard);
        return mapper.map(creditCard, CreditCardResponse.class);
    }

    @Override
    public CreditCardResponse getCreditCard(Long id) throws NotFoundException {

        if (id == null){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CARD_NOT_ID);
        }

        CreditCard creditCard = repository.findById(id).orElseThrow(() -> new NotFoundException(
                ConstantsExceptionMessages.MSG_CC_NOT_FOUD));
        ModelMapper mapper = new ModelMapper();
        return mapper.map(creditCard, CreditCardResponse.class);
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return repository.findAll();
    }

    @Override
    public void deleteCreditCard(Long id) throws NotFoundException {

        if (id == null) {
            throw new BadRequestException(ConstantsExceptionMessages.MSG_CARD_NOT_ID);
        }
         repository.findById(id).orElseThrow(() -> new NotFoundException(
                ConstantsExceptionMessages.MSG_CC_NOT_FOUD));
        repository.deleteById(id);
    }
}
