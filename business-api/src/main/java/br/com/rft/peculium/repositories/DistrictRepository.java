package br.com.rft.peculium.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rft.peculium.models.District;

public interface DistrictRepository extends JpaRepository<District, Long> {
}
