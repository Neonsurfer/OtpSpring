package otp.exceptions;

import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends Exception {
    private final HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

    public UnprocessableEntityException(String message) {
        super(message);
    }
}
