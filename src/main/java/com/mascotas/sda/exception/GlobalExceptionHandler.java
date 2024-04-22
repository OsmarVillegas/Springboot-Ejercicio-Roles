package com.mascotas.sda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        // Aquí puedes verificar si la excepción fue causada por un enum no válido
        if (ex.getRequiredType().isEnum()) {
            return new ResponseEntity<>("Valor no válido para el enum: " + ex.getValue(), HttpStatus.BAD_REQUEST);
        }
        // Para otros tipos de errores de tipo, puedes devolver un mensaje genérico o
        // manejarlos de manera diferente
        return new ResponseEntity<>("Error de tipo de argumento", HttpStatus.BAD_REQUEST);
    }
}
