package com.SmartBooking.exceções;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<?> tratarErro(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
