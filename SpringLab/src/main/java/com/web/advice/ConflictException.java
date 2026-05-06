package com.web.advice; // Adaptado a tu estructura de paquetes (com.web.advice)

public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}