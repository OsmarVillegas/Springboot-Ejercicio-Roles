package com.mascotas.sda.exception;

public class PatternValidationException extends RuntimeException {
    private String field;

    public PatternValidationException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
