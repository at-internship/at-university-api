package com.agilethought.internship.university.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter

public class NotFoundException extends RuntimeException {

    private String message;
    private String path;
    private HttpStatus error;
    private int status;

    public NotFoundException(String message, String path) {
        this.setMessage(message);
        this.setPath(path);
        this.setError(HttpStatus.NOT_FOUND);
        this.setStatus(404);
    }
}
