package com.kshitij.ticket.error;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
