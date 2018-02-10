package br.com.rft.peculium.web.to.converter.entity;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.City;
import br.com.rft.peculium.web.to.CityTO;

@Component
public class CityConverter implements Converter<CityTO, City> {

	@Override
	public City convert(CityTO source) {
		City target = new City();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setState(source.getState());
		target.setCreateBy("root@localhost.com"); //TODO buscar dados do Audity
		target.setCreatedDate(new Date()); //TODO buscar dados do Audity
	
		return target;
	}

}
