package com.luiz.plugtime.exceptions;

import com.luiz.plugtime.dto.ErrorDto;
import com.luiz.plugtime.exceptions.customer.CustomerCreationException;
import com.luiz.plugtime.exceptions.customer.CustomerException;
import com.luiz.plugtime.exceptions.customer.CustomerNotFoundException;
import com.luiz.plugtime.exceptions.employee.EmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDto> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        String message = String.format("Parâmetro inválido para '%s': '%s'. Esperado tipo: %s.",
                e.getName(), e.getValue(), e.getRequiredType().getSimpleName());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(message));
    }

    // Customer Exception
    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorDto> handleCustomerException(CustomerException e){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto(e.getMessage()));
    }

    // Customer Creation Exception
    @ExceptionHandler(CustomerCreationException.class)
    public ResponseEntity<ErrorDto> handleCustomerCreationException(CustomerCreationException e){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto(e.getMessage()));
    }

    // Customer not found
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorDto> handleCustomerNotFoundException(CustomerNotFoundException e){
        return ResponseEntity
                .status(404)
                .body(new ErrorDto(e.getMessage()));
    }

    // Employee Exception
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ErrorDto> handleEmployeeException(EmployeeException e){
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto(e.getMessage()));
    }

}
