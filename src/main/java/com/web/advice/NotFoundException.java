package com.web.advice;

/**
 * Excepción personalizada para cuando no se encuentra un recurso (404).
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}