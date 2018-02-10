package br.com.rft.peculium.services;

import java.util.List;

import br.com.rft.peculium.dtos.UserRecoveryTO;
import br.com.rft.peculium.models.User;

public interface UserAccountService {
    public String registerUser(User user);
    public String recoverPassword(UserRecoveryTO userRecoveryTO);
	public User find(Long id);
	public List<User> getAll();
	public User findByUsername(String username);
	public Object edit(User user);
}
