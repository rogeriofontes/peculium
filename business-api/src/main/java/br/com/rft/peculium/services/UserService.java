package br.com.rft.peculium.services;

import java.util.List;

import br.com.rft.peculium.models.User;

public interface UserService {
	User save(User user);
	User find(Long id);
	List<User> getAll();
	User findByUsername(String username);
	User edit(User user);
}
