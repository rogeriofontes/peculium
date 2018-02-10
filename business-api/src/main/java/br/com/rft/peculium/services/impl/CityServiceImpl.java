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

import br.com.rft.peculium.models.City;
import br.com.rft.peculium.repositories.CityRepository;
import br.com.rft.peculium.services.CityService;
import br.com.rft.peculium.web.to.CityTO;

@Service
public class CityServiceImpl implements CityService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ConversionService conversionService;

	@Override
	public CityTO save(CityTO restaurantTypeTO) {
		logger.info("Saved: " + !StringUtils.isEmpty(restaurantTypeTO.toString()));
		City resultSaved = cityRepository.save(conversionService.convert(restaurantTypeTO, City.class));
		return conversionService.convert(resultSaved, CityTO.class);
	}

	@Override
	public Boolean delete(Long id) {
		City result = cityRepository.findOne(id);
		if (result != null) {
			cityRepository.delete(result);
			return true;
		}
		return false;
	}

	@Override
	public CityTO edit(CityTO restaurantTypeTO, Long id) {
		City result = cityRepository.findOne(id);
		
		City restaurantType = conversionService.convert(restaurantTypeTO, City.class);
		result.update(restaurantType);
		
		City resultUpdated = cityRepository.save(result);
		return conversionService.convert(resultUpdated, CityTO.class);
	}

	@Override
	public CityTO find(Long id) {
		City result = cityRepository.findOne(id);
		return conversionService.convert(result, CityTO.class);
	}

	@Override
	@Cacheable("citysInCache")
	public List<CityTO> getAll() {
		List<CityTO> tos = new ArrayList<>();
		Iterable<City> itr = cityRepository.findAll();
		for (City restaurantType : itr) {
			tos.add(conversionService.convert(restaurantType, CityTO.class));
		}
		return tos;
	}

	@Override
	@Cacheable("citysInCache")
	public Page<CityTO> findAllPageable(Pageable pageable) {
		//return examTypeRepository.findAll(pageable);
		return null;
	}
}
