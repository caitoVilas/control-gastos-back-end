package com.caito.controlgastos.exceptions.customs;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String error){
        super(error);
    }
}
