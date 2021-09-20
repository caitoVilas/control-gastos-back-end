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
    public ResponseEntity<CreditCardResponse> createCreditCard(@RequestBody NewCreditCard newCreditCard)
            throws NotFoundException {

        return new ResponseEntity<CreditCardResponse>(service.createCreditCard(newCreditCard),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardResponse> getCreditCard(@PathVariable("id") Long id) throws NotFoundException {

        return new ResponseEntity<CreditCardResponse>(service.getCreditCard(id), HttpStatus.OK);
    }

   @GetMapping
    public ResponseEntity<List<CreditCardResponse>> getAllCreditCard(){

        return new ResponseEntity<List<CreditCardResponse>>(service.getAllCreditsCard(), HttpStatus.OK);
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCreditCard(@PathVariable("id") Long id) throws NotFoundException {

        service.deleteCreditCard(id);

        return new ResponseEntity(HttpStatus.OK);
   }

   @GetMapping("/my-cards/{user_id}")
   public ResponseEntity<List<CreditCardResponse>> getMyCreditCards(@PathVariable("user_id") Long user_id)
           throws NotFoundException {

        return new ResponseEntity<List<CreditCardResponse>>(service.getMyCreditsCards(user_id), HttpStatus.OK);
   }
}
