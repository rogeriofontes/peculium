package br.com.rft.peculium.web.to.converter.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.Account;
import br.com.rft.peculium.web.to.AccountTO;

@Component
public class AccountTOConverter implements Converter<Account, AccountTO> {

	@Override
	public AccountTO convert(Account source) {
		AccountTO target = new AccountTO();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setMonth(source.getMonth());
		target.setBalance(source.getBalance());
		
		return target;
	}

}
