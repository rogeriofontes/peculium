package br.com.rft.peculium.web.resources;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rft.peculium.dtos.Token;
import br.com.rft.peculium.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationResources {

	@Autowired
	private UserDetailsService customUserDetailsService;

	static final long EXPIRATIONTIME = 864_000_000; // 10 days
	static final String SECRET = "ThisIsASecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Token login(@RequestBody User login) throws ServletException {

		String jwtToken = "";

		if (login.getUsername() == null || login.getPassword() == null) {
			throw new ServletException("Please fill in username and password");
		}

		String email = login.getUsername();
		//String password = login.getPassword();

		UserDetails user = customUserDetailsService.loadUserByUsername(email);

		if (user == null) {
			throw new ServletException("User email not found.");
		}

		//String pwd = user.getPassword();

		/*
		 * if (!password.equals(pwd)) { throw new ServletException(
		 * "Invalid login. Please check your name and password."); }
		 */

		jwtToken = Jwts.builder().setSubject(user.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		// jwtToken = TOKEN_PREFIX + " " + JWT;
		Token t = new Token();
		t.setToken(jwtToken);

		return t;

		/*
		 * return Jwts.builder().setSubject(email).claim("roles",
		 * user.getUsername()).setIssuedAt(new Date())
		 * .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		 */

	}
}
