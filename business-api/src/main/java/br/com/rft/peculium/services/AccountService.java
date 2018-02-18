package br.com.rft.peculium.services;

import br.com.rft.peculium.web.to.AccountTO;

public interface AccountService extends CrudService<AccountTO, Long> {
	AccountTO findByName(String name);
}
