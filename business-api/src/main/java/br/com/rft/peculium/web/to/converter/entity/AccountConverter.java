package br.com.rft.peculium.web.to.converter.entity;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.Account;
import br.com.rft.peculium.web.to.AccountTO;

@Component
public class AccountConverter implements Converter<AccountTO, Account> {

	@Override
	public Account convert(AccountTO source) {
		Account target = new Account();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setMonth(source.getMonth());
		target.setBalance(source.getBalance());
		target.setCreateBy("root@localhost.com"); //TODO buscar dados do Audity
		target.setCreatedDate(new Date()); //TODO buscar dados do Audity
		
		return target;
	}

}
