package br.com.rft.peculium.security;

import br.com.rft.peculium.web.exceptions.NotImplementationConstructionException;

public final class AuthoritiesConstants {
	
	public static final String ANONYMOUS = "ROLE_ANONYMOUS";
	public static final String ADMIN = "ROLE_ADMIN";
	public static final String USER = "ROLE_USER";

	private AuthoritiesConstants() {
		throw new NotImplementationConstructionException("Classe não pode ser instanciada");
	}

}
