package org.example.util.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String code;
    private String message;

    ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
