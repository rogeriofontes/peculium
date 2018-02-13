package br.com.rft.peculium.web.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.rft.peculium.dtos.TokenResult;
import br.com.rft.peculium.dtos.UserRecoveryTO;
import br.com.rft.peculium.models.User;
import br.com.rft.peculium.models.UserRole;
import br.com.rft.peculium.services.UserAccountService;
import br.com.rft.peculium.services.UserService;
import br.com.rft.peculium.util.JWTUtil;
import br.com.rft.peculium.web.exceptions.UserNotFoundException;
import br.com.rft.peculium.web.to.UserTO;

@RestController
@RequestMapping("/auth")
public class AuthResources {

	@Autowired
	private UserDetailsService customUserDetailsService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserAccountService userAccountService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public TokenResult login(@RequestBody UserTO userAuthentication) throws ServletException {

		if (userAuthentication.getUsername() == null || userAuthentication.getPassword() == null) {
			throw new ServletException("Please fill in username and password");
		}

		UserDetails user = customUserDetailsService.loadUserByUsername(userAuthentication.getUsername());

		if (user == null) {
			throw new UserNotFoundException("User not found.");
		}

		User userResult = userService.findByUsername(userAuthentication.getUsername());

		return setResultLoginInfo(userAuthentication, userResult);
	}

	private TokenResult setResultLoginInfo(UserTO userAuthentication, User userResult) {
		TokenResult tokenResult = new TokenResult();
		tokenResult.setToken(JWTUtil.createToken(userAuthentication.getUsername()));
		tokenResult.setUserId(userResult.getId());

		List<String> rolesResult = new ArrayList<>();
		Set<UserRole> roles = userResult.getRoles();
		for (UserRole userRole : roles) {
			rolesResult.add(userRole.getRoleName());
		}
		tokenResult.setRoles(rolesResult);
		return tokenResult;
	}

	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> resetPassword(@RequestParam("email") String email) {
		String response = null;

		User user = userService.findByUsername(email);
		if (user == null) {
			throw new UserNotFoundException("Usuário não encontrado!");
		}

		UserRecoveryTO userRecovery = userAccountService.recoverPassword(email);
		if (userRecovery != null) {
			response = "Senha Resetada, confira seu e-mail" + "Dados Usuario: " + userRecovery.toString();
		} else {
			response = "Não foi possivel resetar a senha, confira seu e-mail";
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/change-password", method = RequestMethod.GET)
	public String showChangePasswordPage(@RequestParam("id") long id, @RequestParam("token") String token) {
		String newPassword = null;

		User user = userService.find(id);
		if (user == null) {
			throw new UserNotFoundException("Usuário não encontrado!");
		} else {
			String result = userAccountService.validatePasswordResetToken(user, token);
			if (result != null) {
				newPassword = userAccountService.changeUserPassword(user, token);
			}
		}
		return newPassword;
	}

}
