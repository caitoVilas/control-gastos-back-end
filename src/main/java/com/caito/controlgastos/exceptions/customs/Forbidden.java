package com.caito.controlgastos.exceptions.customs;

public class Forbidden extends RuntimeException{

    public Forbidden(String error){

        super(error);
    }
}
