package com.sparkx.ecommerce_api.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {

        super(message);
    }
}
