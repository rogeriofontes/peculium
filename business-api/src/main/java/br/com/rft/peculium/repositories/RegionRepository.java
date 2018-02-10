package br.com.rft.peculium.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rft.peculium.models.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
