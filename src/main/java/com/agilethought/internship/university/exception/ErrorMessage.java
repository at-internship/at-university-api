package com.agilethought.internship.university.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorMessage{

    public LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorMessage(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

}
