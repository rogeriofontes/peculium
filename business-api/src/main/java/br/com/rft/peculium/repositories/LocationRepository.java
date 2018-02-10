package br.com.rft.peculium.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rft.peculium.models.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
