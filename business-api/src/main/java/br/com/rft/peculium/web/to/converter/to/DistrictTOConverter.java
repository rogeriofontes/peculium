package br.com.rft.peculium.web.to.converter.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.District;
import br.com.rft.peculium.web.to.DistrictTO;

@Component
public class DistrictTOConverter implements Converter<District, DistrictTO> {

	@Override
	public DistrictTO convert(District source) {
		DistrictTO target = new DistrictTO();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setCity(source.getCity());
	
		return target;
	}

}
