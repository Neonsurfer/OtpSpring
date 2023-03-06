package otp.exceptions;

import org.springframework.http.HttpStatus;

public class FoundException extends Exception {
    private final HttpStatus httpStatus = HttpStatus.FOUND;

    public FoundException(String message) {
        super(message);
    }
}
