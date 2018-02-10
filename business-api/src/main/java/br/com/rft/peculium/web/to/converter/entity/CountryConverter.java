package br.com.rft.peculium.web.to.converter.entity;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.Country;
import br.com.rft.peculium.web.to.CountryTO;

@Component
public class CountryConverter implements Converter<CountryTO, Country> {

	@Override
	public Country convert(CountryTO source) {
		Country target = new Country();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setRegion(source.getRegion());
		target.setCreateBy("root@localhost.com"); //TODO buscar dados do Audity
		target.setCreatedDate(new Date()); //TODO buscar dados do Audity
	
		return target;
	}

}
