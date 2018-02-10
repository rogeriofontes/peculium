package br.com.rft.peculium.services;

import br.com.rft.peculium.models.User;
import br.com.rft.peculium.web.to.CurrentUser;

public interface CurrentUserService {
	CurrentUser getDataToCurrentUser();

	User loadCurrentUser();
}
