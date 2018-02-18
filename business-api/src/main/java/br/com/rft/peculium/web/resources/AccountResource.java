package br.com.rft.peculium.web.resources;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.rft.peculium.services.AccountService;
import br.com.rft.peculium.web.to.AccountTO;

@RestController
@RequestMapping("/accounts")
public class AccountResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountService accountService;

/**
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLIC	@ApiOperation(notes = "Returns a Accounts  when 0 < ID <= 10.  ID > 10 or non-integers will simulate API error conditions", value = "Find Account s by ID", nickname = "getAccountById", tags = {
			"Account" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Nice!", response = Account.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied", response = Account.class),
			@ApiResponse(code = 404, message = "Pet not found", response = Account.class) })ATION_JSON_VALUE) */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<AccountTO>> list() {
		List<AccountTO> accounts = accountService.getAll();
		logger.info("Finded Account s: %s", !StringUtils.isEmpty(accounts.toString()));
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<AccountTO> getById(@PathVariable("id") Long id) {
		AccountTO account = accountService.find(id);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "accountsInCache", allEntries = true)
	public ResponseEntity<AccountTO> add(@RequestBody AccountTO account) {
		AccountTO saved = accountService.save(account);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/find-by-name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<AccountTO> getByName(@PathVariable("name") String name) {
		AccountTO saved = accountService.findByName(name);
		return new ResponseEntity<>(saved, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "accountsInCache", allEntries = true)
	public ResponseEntity<AccountTO> update(@PathVariable("id") Long id,
			@RequestBody AccountTO account) {
		AccountTO saved = accountService.edit(account, id);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@CacheEvict(value = "accountsInCache", allEntries = true)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		accountService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
