package br.com.rft.peculium.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rft.peculium.models.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
