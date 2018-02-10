package br.com.rft.peculium.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rft.peculium.models.State;

public interface StateRepository extends JpaRepository<State, Long> {
}
