package com.agilethought.internship.university.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter

public class BadRequestException extends RuntimeException{

    public String message;
    private String path;
    private HttpStatus error;
    private int status;

    public BadRequestException(String message, String path) {
        this.setMessage(message);
        this.setPath(path);
        this.setError(HttpStatus.BAD_REQUEST);
        this.setStatus(400);
    }

}

