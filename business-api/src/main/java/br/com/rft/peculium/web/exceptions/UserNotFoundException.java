package br.com.rft.peculium.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Usuário não encontrado.")
public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 8945783223488005558L;

	public UserNotFoundException(final String message) {
	        super(message);
	}

}
