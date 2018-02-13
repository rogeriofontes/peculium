package br.com.rft.peculium.services;

import br.com.rft.peculium.dtos.UserRecoveryTO;
import br.com.rft.peculium.models.User;
import br.com.rft.peculium.web.to.UserTO;

public interface UserAccountService {
    UserRecoveryTO recoverPassword(String email);
	String validatePasswordResetToken(User user, String token);
	public String changeUserPassword(User user, String token);
	public boolean register(UserTO userTO);
}
