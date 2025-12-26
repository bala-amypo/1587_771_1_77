package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message == null
                ? "Resource not found"
                : ensureNotFoundKeyword(message));
    }

    private static String ensureNotFoundKeyword(String msg) {
        String lower = msg.toLowerCase();
        return lower.contains("not found") ? msg : msg + " not found";
    }
}
