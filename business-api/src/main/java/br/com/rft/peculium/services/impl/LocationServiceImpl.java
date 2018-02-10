package br.com.rft.peculium.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.rft.peculium.models.Location;
import br.com.rft.peculium.repositories.LocationRepository;
import br.com.rft.peculium.services.LocationService;
import br.com.rft.peculium.web.to.LocationTO;

@Service
public class LocationServiceImpl implements LocationService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private ConversionService conversionService;

	@Override
	public LocationTO save(LocationTO locationTO) {
		logger.info("Saved: " + !StringUtils.isEmpty(locationTO.toString()));
		Location resultSaved = locationRepository.save(conversionService.convert(locationTO, Location.class));
		return conversionService.convert(resultSaved, LocationTO.class);
	}

	@Override
	public Boolean delete(Long id) {
		Location result = locationRepository.findOne(id);
		if (result != null) {
			locationRepository.delete(result);
			return true;
		}
		return false;
	}

	@Override
	public LocationTO edit(LocationTO locationTO, Long id) {
		Location result = locationRepository.findOne(id);
		
		Location restaurantType = conversionService.convert(locationTO, Location.class);
		result.update(restaurantType);
		
		Location resultUpdated = locationRepository.save(result);
		return conversionService.convert(resultUpdated, LocationTO.class);
	}

	@Override
	public LocationTO find(Long id) {
		Location result = locationRepository.findOne(id);
		return conversionService.convert(result, LocationTO.class);
	}

	@Override
	@Cacheable("locationsInCache")
	public List<LocationTO> getAll() {
		List<LocationTO> tos = new ArrayList<>();
		Iterable<Location> itr = locationRepository.findAll();
		for (Location restaurantType : itr) {
			tos.add(conversionService.convert(restaurantType, LocationTO.class));
		}
		return tos;
	}

	@Override
	@Cacheable("locationsInCache")
	public Page<LocationTO> findAllPageable(Pageable pageable) {
		//return examTypeRepository.findAll(pageable);
		return null;
	}
}
