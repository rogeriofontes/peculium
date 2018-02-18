package br.com.rft.peculium.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rft.peculium.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByName(String name);
}
