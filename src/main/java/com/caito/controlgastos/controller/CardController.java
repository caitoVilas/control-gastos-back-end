package com.caito.controlgastos.controller;

import com.caito.controlgastos.dto.CardResponse;
import com.caito.controlgastos.dto.NewCard;
import com.caito.controlgastos.service.impl.CardService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/card")
@CrossOrigin
public class CardController {

    @Autowired
    private CardService service;

    @PostMapping
    public ResponseEntity<CardResponse> createCard(@RequestBody NewCard newCard){

        return new ResponseEntity<CardResponse>(service.createCard(newCard), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponse> getCard(@PathVariable("id") Long id) throws NotFoundException {

        return new ResponseEntity<CardResponse>(service.getCard(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CardResponse>> getAllCards(){

        return new ResponseEntity<List<CardResponse>>(service.getAllCards(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCard(@PathVariable("id")Long id) throws NotFoundException {

        service.deleteCard(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
