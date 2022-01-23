package com.example.exchange.exceptions;

public class BadParamsException extends RuntimeException{
    public Reason reason;
    public BadParamsException(String message, Reason reason) {
        super(message);
        this.reason = reason;
    }

    public Reason getReason() {
        return reason;
    }

    public enum Reason {
        BAD_PARAMS,
        BAD_FORMAT
    }
}