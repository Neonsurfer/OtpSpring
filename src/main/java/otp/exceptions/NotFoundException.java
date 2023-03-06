package otp.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception {
    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
    }
}
