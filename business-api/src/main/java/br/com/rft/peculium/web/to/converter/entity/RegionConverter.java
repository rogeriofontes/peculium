package br.com.rft.peculium.web.to.converter.entity;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.Region;
import br.com.rft.peculium.web.to.RegionTO;

@Component
public class RegionConverter implements Converter<RegionTO, Region> {

	@Override
	public Region convert(RegionTO source) {
		Region target = new Region();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setCreateBy("root@localhost.com"); //TODO buscar dados do Audity
		target.setCreatedDate(new Date()); //TODO buscar dados do Audity
	
		return target;
	}

}
