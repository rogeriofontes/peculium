package br.com.rft.peculium.web.to.converter.entity;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.Location;
import br.com.rft.peculium.web.to.LocationTO;

@Component
public class LocationConverter implements Converter<LocationTO, Location> {

	@Override
	public Location convert(LocationTO source) {
		Location target = new Location();

		target.setId(source.getId());
		target.setStreet(source.getStreet());
		target.setComplement(source.getComplement());
		target.setZipcode(source.getZipcode());
		target.setDistrict(source.getDistrict());
		target.setLatitude(source.getLatitude());
		target.setLongitude(source.getLongitude());
		target.setCreateBy("root@localhost.com"); //TODO buscar dados do Audity
		target.setCreatedDate(new Date()); //TODO buscar dados do Audity
		
		return target;
	}

}
