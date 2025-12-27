
// package com.example.demo.exception;

// import org.springframework.web.bind.annotation.*;

// @RestControllerAdvice
// public class ApiExceptionHandler {

//     @ExceptionHandler(Exception.class)
//     public String handle(Exception ex) {
//         return ex.getMessage();
//     }
// }




package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApiExceptionHandler {

    // ADDED: Handler for Validation errors (Duplicate Email, Invalid Score, etc.)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgument(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    // ADDED: Handler for Missing entities (user, profile, skill)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFound(ResourceNotFoundException ex) {
        return ex.getMessage();
    }

    // EDITED: Existing generic handler kept for other exceptions
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle(Exception ex) {
        return ex.getMessage();
    }
}