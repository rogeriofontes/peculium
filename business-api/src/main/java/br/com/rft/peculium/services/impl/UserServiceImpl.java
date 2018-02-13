package br.com.rft.peculium.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rft.peculium.models.User;
import br.com.rft.peculium.repositories.UserRepository;
import br.com.rft.peculium.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public User find(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User edit(User user) {
		User result = find(user.getId());
		result.update(user);
		return userRepository.save(user);
	}
}
