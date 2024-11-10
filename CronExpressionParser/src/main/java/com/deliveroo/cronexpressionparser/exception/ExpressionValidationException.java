package com.deliveroo.cronexpressionparser.exception;

public class ExpressionValidationException extends Exception {
    private static final long serialVersionUID = 1L;

    ExpressionValidationException() {
        super();
    }

    public ExpressionValidationException(String message) {
        super(message);
    }

    ExpressionValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    ExpressionValidationException(Throwable cause) {
        super(cause);
    }
}