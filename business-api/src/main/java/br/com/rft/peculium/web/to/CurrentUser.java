package br.com.rft.peculium.web.to;

import java.util.Set;

import br.com.rft.peculium.models.UserRole;

public class CurrentUser {
	private String name;
	private String email;
	private Set<UserRole> role;

	public CurrentUser() {
		super();
	}

	public CurrentUser(String name, String email, Set<UserRole> role) {
		super();
		this.name = name;
		this.email = email;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Set<UserRole> getRole() {
		return role;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(Set<UserRole> role) {
		this.role = role;
	}

}
