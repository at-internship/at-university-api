package com.agilethought.internship.university.controller;

import com.agilethought.internship.university.exception.BadRequestException;
import com.agilethought.internship.university.exception.ErrorMessage;
import com.agilethought.internship.university.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Global Exception Handler
    @ExceptionHandler
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorMessage error) {
        return new ResponseEntity<>(error, HttpStatus.valueOf(error.getStatus()));
    }

    //Custom Exception
    @ExceptionHandler
    public ResponseEntity<Object> handleBadRequestException(BadRequestException exception) {
        return buildResponseEntity(
                new ErrorMessage(
                        HttpStatus.BAD_REQUEST,
                        exception.getMessage(),
                        exception.getPath().toString()));
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleEntityNotFoundException(NotFoundException exception) {
        return buildResponseEntity(
                new ErrorMessage(HttpStatus.NOT_FOUND,
                        exception.getMessage(),
                        exception.getPath().toString()));

    }
}
