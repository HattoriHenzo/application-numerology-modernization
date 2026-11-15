package com.modernization.application.numerology.service.exception;

public class NumerologyCustomException extends RuntimeException {

    public NumerologyCustomException() {
        super();
    }

    public NumerologyCustomException(String message) {
        super(message);
    }

    public NumerologyCustomException(String message, Throwable exception) {
        super(message, exception);
    }
}
