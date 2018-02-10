package br.com.rft.peculium.web.to.converter.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.Establishment;
import br.com.rft.peculium.web.to.EstablishmentTO;

@Component
public class EstablishmentTOConverter implements Converter<Establishment, EstablishmentTO> {

	@Override
	public EstablishmentTO convert(Establishment source) {
		EstablishmentTO target = new EstablishmentTO();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setLocation(source.getLocation());
		
		return target;
	}

}
