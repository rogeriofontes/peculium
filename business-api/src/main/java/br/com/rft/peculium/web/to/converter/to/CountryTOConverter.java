package br.com.rft.peculium.web.to.converter.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.Country;
import br.com.rft.peculium.web.to.CountryTO;

@Component
public class CountryTOConverter implements Converter<Country, CountryTO> {

	@Override
	public CountryTO convert(Country source) {
		CountryTO target = new CountryTO();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setRegion(source.getRegion());
	
		return target;
	}

}
