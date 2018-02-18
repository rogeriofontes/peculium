package br.com.rft.peculium.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rft.peculium.models.Establishment;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

	Establishment findByName(String name);
}
