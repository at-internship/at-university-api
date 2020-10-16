package com.agilethought.internship.university.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorMessage{

    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorMessage(Date timestamp, int status, String error, String message, String path) {
        this.status = status;
        this.timestamp = timestamp;
        this.error = error;
        this.message = message;
        this.path = path;
    }

}
