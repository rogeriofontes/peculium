package br.com.rft.peculium.constants;

import br.com.rft.peculium.web.exceptions.NotImplementationConstructionException;

public class AppConstants {

	// Auxs Constants for Controllers
	public static final String RESPONSE_UNSUCCESS = "unsuccess";

	public static final String RESPONSE_SUCCESS = "success";

	public static final String CURRENT_USER = "currentUser";

	private AppConstants() {
		throw new NotImplementationConstructionException("Classe não pode ser instanciada");
	}

}
