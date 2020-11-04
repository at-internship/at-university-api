package com.agilethought.internship.university.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorMessage{

    public LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorMessage(HttpStatus error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = error.value();
        this.error = error.getReasonPhrase();
        this.message = message;
        this.path = path;
    }

}
