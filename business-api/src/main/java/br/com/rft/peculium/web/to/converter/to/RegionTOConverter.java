package br.com.rft.peculium.web.to.converter.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.Region;
import br.com.rft.peculium.web.to.RegionTO;

@Component
public class RegionTOConverter implements Converter<Region, RegionTO> {

	@Override
	public RegionTO convert(Region source) {
		RegionTO target = new RegionTO();

		target.setId(source.getId());
		target.setName(source.getName());
	
		return target;
	}

}
