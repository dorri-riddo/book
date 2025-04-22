package org.example.util.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseCustomStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseCustomStatusException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getCode());

        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }
}

