package org.example.util.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class BadRequestException extends RuntimeException {
    private String code;
    private String message;
    
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
