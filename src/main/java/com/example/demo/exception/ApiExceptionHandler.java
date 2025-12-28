
package com.example.demo.exception;

import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handle(Exception ex) {
        return ex.getMessage();
    }
}
