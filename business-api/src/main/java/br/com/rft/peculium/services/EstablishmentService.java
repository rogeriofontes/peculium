package br.com.rft.peculium.services;

import br.com.rft.peculium.web.to.EstablishmentTO;

public interface EstablishmentService extends CrudService<EstablishmentTO, Long> {

	EstablishmentTO findByName(String name);
}
