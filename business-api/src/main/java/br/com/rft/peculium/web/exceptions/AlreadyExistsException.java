package br.com.rft.peculium.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Already Exists Exception.")
public class AlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AlreadyExistsException(final String message) {
        super(message);
    }

}
