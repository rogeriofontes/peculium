package br.com.rft.peculium.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rft.peculium.models.User;
import br.com.rft.peculium.services.CurrentUserService;
import br.com.rft.peculium.services.CustomUserDetailsService;
import br.com.rft.peculium.web.to.CurrentUser;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	public CurrentUser getDataToCurrentUser() {
		User user = this.customUserDetailsService.loadCurrentUser();
		return new CurrentUser(user.getUsername(), user.getUsername(), user.getRoles());
	}

	@Override
	public User loadCurrentUser() {
		return this.customUserDetailsService.loadCurrentUser();
	}

}
