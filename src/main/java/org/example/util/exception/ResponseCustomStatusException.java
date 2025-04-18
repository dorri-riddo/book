package org.example.util.exception;

import jakarta.annotation.Nullable;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class ResponseCustomStatusException extends RuntimeException {
    private String message;
    private HttpStatusCode statusCode;
    private String code;

    public ResponseCustomStatusException(String message, HttpStatusCode statusCode, String code) {
        this.message = message;
        this.statusCode = statusCode;
        this.code = code;
    }

    public ResponseCustomStatusException(String message, HttpStatusCode statusCode) {
        this(message, statusCode, null);
    }
}
