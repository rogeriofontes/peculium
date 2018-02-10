package br.com.rft.peculium.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.rft.peculium.models.Account;
import br.com.rft.peculium.repositories.AccountRepository;
import br.com.rft.peculium.services.AccountService;
import br.com.rft.peculium.web.to.AccountTO;

@Service
public class AccountServiceImpl implements AccountService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ConversionService conversionService;

	@Override
	public AccountTO save(AccountTO accountTO) {
		logger.info("Saved: " + !StringUtils.isEmpty(accountTO.toString()));
		Account resultSaved = accountRepository.save(conversionService.convert(accountTO, Account.class));
		return conversionService.convert(resultSaved, AccountTO.class);
	}

	@Override
	public Boolean delete(Long id) {
		Account result = accountRepository.findOne(id);
		if (result != null) {
			accountRepository.delete(result);
			return true;
		}
		return false;
	}

	@Override
	public AccountTO edit(AccountTO accountTO, Long id) {
		Account result = accountRepository.findOne(id);

		Account account = conversionService.convert(accountTO, Account.class);
		result.update(account);

		Account resultUpdated = accountRepository.save(result);
		return conversionService.convert(resultUpdated, AccountTO.class);
	}

	@Override
	public AccountTO find(Long id) {
		Account result = accountRepository.findOne(id);
		return conversionService.convert(result, AccountTO.class);
	}

	@Override
	@Cacheable("accountsInCache")
	public List<AccountTO> getAll() {
		List<AccountTO> tos = new ArrayList<>();
		Iterable<Account> itr = accountRepository.findAll();
		for (Account account : itr) {
			tos.add(conversionService.convert(account, AccountTO.class));
		}
		return tos;
	}

	@Override
	@Cacheable("accountsInCache")
	public Page<AccountTO> findAllPageable(Pageable pageable) {
		// return examTypeRepository.findAll(pageable);
		return null;
	}
}
