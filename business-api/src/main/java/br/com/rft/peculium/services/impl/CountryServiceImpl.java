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

import br.com.rft.peculium.models.Country;
import br.com.rft.peculium.repositories.CountryRepository;
import br.com.rft.peculium.services.CountryService;
import br.com.rft.peculium.web.to.CountryTO;

@Service
public class CountryServiceImpl implements CountryService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private ConversionService conversionService;

	@Override
	public CountryTO save(CountryTO restaurantTypeTO) {
		logger.info("Saved: " + !StringUtils.isEmpty(restaurantTypeTO.toString()));
		Country resultSaved = countryRepository.save(conversionService.convert(restaurantTypeTO, Country.class));
		return conversionService.convert(resultSaved, CountryTO.class);
	}

	@Override
	public Boolean delete(Long id) {
		Country result = countryRepository.findOne(id);
		if (result != null) {
			countryRepository.delete(result);
			return true;
		}
		return false;
	}

	@Override
	public CountryTO edit(CountryTO restaurantTypeTO, Long id) {
		Country result = countryRepository.findOne(id);
		
		Country restaurantType = conversionService.convert(restaurantTypeTO, Country.class);
		result.update(restaurantType);
		
		Country resultUpdated = countryRepository.save(result);
		return conversionService.convert(resultUpdated, CountryTO.class);
	}

	@Override
	public CountryTO find(Long id) {
		Country result = countryRepository.findOne(id);
		return conversionService.convert(result, CountryTO.class);
	}

	@Override
	@Cacheable("countrysInCache")
	public List<CountryTO> getAll() {
		List<CountryTO> tos = new ArrayList<>();
		Iterable<Country> itr = countryRepository.findAll();
		for (Country restaurantType : itr) {
			tos.add(conversionService.convert(restaurantType, CountryTO.class));
		}
		return tos;
	}

	@Override
	@Cacheable("countrysInCache")
	public Page<CountryTO> findAllPageable(Pageable pageable) {
		//return examTypeRepository.findAll(pageable);
		return null;
	}
}
