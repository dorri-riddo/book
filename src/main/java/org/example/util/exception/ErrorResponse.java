package org.example.util.exception;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String code;

    ErrorResponse(String message, @Nullable String code) {
        this.message = message;
        this.code = code;
    }
}
