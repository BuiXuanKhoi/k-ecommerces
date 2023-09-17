package com.ecommerce.customerservice.exceptions;

public class WrongCredentialException extends RuntimeException{

    public WrongCredentialException() {
        super("Wrong Credential");
    }

    public WrongCredentialException(String message) {
        super(message);
    }
}
