package com.banking.BankApplication.exception;

import org.springframework.validation.Errors;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Exception ex) {
        super(message, ex);
        System.out.print("Resource Not Found Exception occurred for message: " + message);
        System.out.println(", exception:  " + ex);
    }

}