package br.com.rft.peculium.web.to.converter.entity;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.District;
import br.com.rft.peculium.web.to.DistrictTO;

@Component
public class DistrictConverter implements Converter<DistrictTO, District> {

	@Override
	public District convert(DistrictTO source) {
		District target = new District();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setCity(source.getCity());
		target.setCreateBy("root@localhost.com"); //TODO buscar dados do Audity
		target.setCreatedDate(new Date()); //TODO buscar dados do Audity
	
		return target;
	}

}
