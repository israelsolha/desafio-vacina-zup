package com.israelsolha.vacinas.controllers.exceptions;

import com.israelsolha.vacinas.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> preValidation(HttpMessageNotReadableException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Corpo da " +
                "requisição vazio ou mal formatado", request.getRequestURI().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro " +
                "de validação", request.getRequestURI().toString());
        List<FieldMessage> uniqueErrors = new ArrayList<>();
        String errorMessage;
        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            if (x.getCode().equals("UniqueField")) {
                uniqueErrors.add(new FieldMessage(x.getField(), x.getField() + " já existente"));
            } else {
                err.addError(x.getField(), x.getDefaultMessage());
            }
        }
        if (!err.getErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
        err.getErrors().clear();
        err.getErrors().addAll(uniqueErrors);
        err.setStatus(HttpStatus.CONFLICT.value());
        err.setMsg("Erro de integridade");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> redundantUniqueValidation(DataIntegrityViolationException e,
                                                                   HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.CONFLICT.value(), "Erro de " +
                "integridade, campo duplicado", request.getRequestURI().toString());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundValidation(ObjectNotFoundException e,
                                                                  HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.PRECONDITION_FAILED.value(),
                e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(err);
    }

}
