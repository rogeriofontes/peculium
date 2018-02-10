package br.com.rft.peculium.web.to.converter.entity;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.Establishment;
import br.com.rft.peculium.web.to.EstablishmentTO;

@Component
public class EstablishmentConverter implements Converter<EstablishmentTO, Establishment> {

	@Override
	public Establishment convert(EstablishmentTO source) {
		Establishment target = new Establishment();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setLocation(source.getLocation());
		target.setCreateBy("root@localhost.com"); //TODO buscar dados do Audity
		target.setCreatedDate(new Date()); //TODO buscar dados do Audity
		
		return target;
	}

}
