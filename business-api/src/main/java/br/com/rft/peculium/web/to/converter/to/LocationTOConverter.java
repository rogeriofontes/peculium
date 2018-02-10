package br.com.rft.peculium.web.to.converter.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.Location;
import br.com.rft.peculium.web.to.LocationTO;

@Component
public class LocationTOConverter implements Converter<Location, LocationTO> {

	@Override
	public LocationTO convert(Location source) {
		LocationTO target = new LocationTO();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setStreet(source.getStreet());
		target.setComplement(source.getComplement());
		target.setZipcode(source.getZipcode());
		target.setDistrict(source.getDistrict());
		target.setLatitude(source.getLatitude());
		target.setLongitude(source.getLongitude());
		
		return target;
	}

}
