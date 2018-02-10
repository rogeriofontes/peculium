package br.com.rft.peculium.web.to.converter.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.City;
import br.com.rft.peculium.web.to.CityTO;

@Component
public class CityTOConverter implements Converter<City, CityTO> {

	@Override
	public CityTO convert(City source) {
		CityTO target = new CityTO();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setState(source.getState());
	
		return target;
	}

}
