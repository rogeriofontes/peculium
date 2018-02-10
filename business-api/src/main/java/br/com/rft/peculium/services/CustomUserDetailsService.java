package br.com.rft.peculium.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.rft.peculium.models.User;
import br.com.rft.peculium.models.UserRole;
import br.com.rft.peculium.repositories.UserRepository;
import br.com.rft.peculium.repositories.UserRoleRepository;
import br.com.rft.peculium.util.SecurityUtils;

@Service
@Qualifier("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public UserDetails loadUserByUsername(final String email) {

		User user = userRepository.findByEmail(email);
		Set<UserRole> roles = userRoleRepository.findRoleNameByUserId(user.getId());
		List<GrantedAuthority> authorities = buildUserAuthority(roles);
		
		return buildUserForAuthentication(user, authorities);

	}

	@Transactional
	public User loadCurrentUser() {
		return userRepository.findByUsername(SecurityUtils.getCurrentLogin());
	}

	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
				true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRoleName()));
		}

		return new ArrayList<>(setAuths);
	}

}
