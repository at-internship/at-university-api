package com.agilethought.internship.university.controller;

import com.agilethought.internship.university.exception.BadRequestException;
import com.agilethought.internship.university.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Global Exception Handler
    @ExceptionHandler
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
                ErrorMessage message = new ErrorMessage(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
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
                new ErrorMessage(LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        exception.getMessage(),
                        exception.getPath().toString()));
    }

}
