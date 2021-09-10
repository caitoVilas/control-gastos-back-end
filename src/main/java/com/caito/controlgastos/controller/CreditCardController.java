package com.caito.controlgastos.controller;

import com.caito.controlgastos.dto.CreditCardResponse;
import com.caito.controlgastos.dto.NewCreditCard;
import com.caito.controlgastos.entity.CreditCard;
import com.caito.controlgastos.service.impl.CreditCardService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/credit-card")
@CrossOrigin
public class CreditCardController {

    @Autowired
    private CreditCardService service;

    @PostMapping
    public ResponseEntity<CreditCardResponse> createCreditCard(NewCreditCard newCreditCard) throws NotFoundException {

        return new ResponseEntity<CreditCardResponse>(service.createCreditCard(newCreditCard),
                                                      HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardResponse> getCreditCard(@PathVariable("id") Long id) throws NotFoundException {

        return new ResponseEntity<CreditCardResponse>(service.getCreditCard(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CreditCard>> getAllCreditcards(){

        return new ResponseEntity<List<CreditCard>>(service.getAllCreditCards(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCreditCard(@PathVariable("id") Long id) throws NotFoundException {

        service.deleteCreditCard(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
