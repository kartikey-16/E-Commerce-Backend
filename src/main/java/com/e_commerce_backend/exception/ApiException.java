package com.e_commerce_backend.exception;

public class ApiException extends  RuntimeException{

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }
}
