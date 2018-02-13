package br.com.rft.peculium.web.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.rft.peculium.services.UserAccountService;
import br.com.rft.peculium.web.to.UserTO;

@RestController
@RequestMapping("/register")
public class RegisterResources {

	@Autowired
	private UserAccountService userAccountService;

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<Boolean> register(@RequestBody UserTO userTO) {
		
		boolean registered = userAccountService.register(userTO);
		return new ResponseEntity<>(registered, HttpStatus.CREATED);
	}
}