package br.com.rft.peculium.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rft.peculium.models.Movement;

public interface MovementRepository extends JpaRepository<Movement, Long> {
}
