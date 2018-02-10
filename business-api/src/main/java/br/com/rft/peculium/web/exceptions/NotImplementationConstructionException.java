package br.com.rft.peculium.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Not Implementation Construction Exception.")
public class NotImplementationConstructionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotImplementationConstructionException(final String message) {
        super(message);
    }

}
