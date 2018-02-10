package br.com.rft.peculium.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rft.peculium.models.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
