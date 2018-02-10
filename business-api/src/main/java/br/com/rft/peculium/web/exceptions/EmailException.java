package br.com.rft.peculium.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email Exception.")
public class EmailException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailException(final String message) {
        super(message);
    }

}
