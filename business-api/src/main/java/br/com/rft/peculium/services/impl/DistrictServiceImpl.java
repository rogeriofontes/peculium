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

import br.com.rft.peculium.models.District;
import br.com.rft.peculium.repositories.DistrictRepository;
import br.com.rft.peculium.services.DistrictService;
import br.com.rft.peculium.web.to.DistrictTO;

@Service
public class DistrictServiceImpl implements DistrictService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private ConversionService conversionService;

	@Override
	public DistrictTO save(DistrictTO districtTO) {
		logger.info("Saved: " + !StringUtils.isEmpty(districtTO.toString()));
		District resultSaved = districtRepository.save(conversionService.convert(districtTO, District.class));
		return conversionService.convert(resultSaved, DistrictTO.class);
	}

	@Override
	public Boolean delete(Long id) {
		District result = districtRepository.findOne(id);
		if (result != null) {
			districtRepository.delete(result);
			return true;
		}
		return false;
	}

	@Override
	public DistrictTO edit(DistrictTO districtTO, Long id) {
		District result = districtRepository.findOne(id);
		
		District restaurantType = conversionService.convert(districtTO, District.class);
		result.update(restaurantType);
		
		District resultUpdated = districtRepository.save(result);
		return conversionService.convert(resultUpdated, DistrictTO.class);
	}

	@Override
	public DistrictTO find(Long id) {
		District result = districtRepository.findOne(id);
		return conversionService.convert(result, DistrictTO.class);
	}

	@Override
	@Cacheable("districtsInCache")
	public List<DistrictTO> getAll() {
		List<DistrictTO> tos = new ArrayList<>();
		Iterable<District> itr = districtRepository.findAll();
		for (District restaurantType : itr) {
			tos.add(conversionService.convert(restaurantType, DistrictTO.class));
		}
		return tos;
	}

	@Override
	@Cacheable("districtsInCache")
	public Page<DistrictTO> findAllPageable(Pageable pageable) {
		//return examTypeRepository.findAll(pageable);
		return null;
	}
}
