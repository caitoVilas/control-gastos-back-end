package com.caito.controlgastos.constants;

public class ConstantsExceptionMessages {

    public static final String MSG_USER_NOT_FOUND = "No existe el usuario ";
    public static final String MSG_USER_USERNAME_EMPTY = "El campo nombre de usuario es requerido";
    public static final String MSG_USER_USERNAME_EXISTS = "El nombre de usuario ya esta registrado";
    public static final String MSG_USER_EMAIL_EMPTY = "El email es requerido";
    public static final String MSG_USER_EMAIL_EXIST = "El email ya esta registrado";
    public static final String MSG_USER_PASSWORD_EMPTY = "La contraseña es requerida";

    // INSTITUTIONS

    public static final String MSG_INST_EXISTS_BY_NAME = "El nombre de la Entidad ya esta registrado!";
    public static final String MSG_INST_NAME_EMPTY = "El nombre es requerido";
    public static final String MSG_INST_NOT_ID = "Se necesita el id";
    public static final String MSG_INST_NOT_FOUND = "La Entidad no existe!";
    public static final String MSG_INST_IN_USE = "La institucion esta en uso!";
    //CARD

    public static final String MSG_CARD_EXISTS_BY_NAME = "El nombre de la tarjeta ya esta registrado!";
    public static final String MSG_CARD_NAME_EMPTY = "el campo nombre es requerido";
    public static final String MSG_CARD_NOT_ID = "Se necesita el id";
    public static final String MSG_CARD_NOT_FOUND = "La tarjeta no existe!";
    public static final String MSG_CARD_IN_USE = "La tarjeta esta en uso";

    //CREDIT CARDS

    public static final String MSG_CC_NOT_CARD_ID = "El id de la tarjeta es requerido";
    public static final String MSG_CC_NOT_INST_ID = "El id de la institucion es requerido";
    public static final String MSG_CC_NOT_USEER_ID = "El id del usuario es requerido";
    public static final String MSG_CC_EXISTS = "La Tarjeta ya esta registrada!";
    public static final String MSG_CC_NOT_FOUD = "La tarjeta no existe!";
 }
