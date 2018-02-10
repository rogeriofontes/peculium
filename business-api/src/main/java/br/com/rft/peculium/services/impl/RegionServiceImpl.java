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

import br.com.rft.peculium.models.Region;
import br.com.rft.peculium.repositories.RegionRepository;
import br.com.rft.peculium.services.RegionService;
import br.com.rft.peculium.web.to.RegionTO;

@Service
public class RegionServiceImpl implements RegionService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private ConversionService conversionService;

	@Override
	public RegionTO save(RegionTO regionTO) {
		logger.info("Saved: " + !StringUtils.isEmpty(regionTO.toString()));
		Region resultSaved = regionRepository.save(conversionService.convert(regionTO, Region.class));
		return conversionService.convert(resultSaved, RegionTO.class);
	}

	@Override
	public Boolean delete(Long id) {
		Region result = regionRepository.findOne(id);
		if (result != null) {
			regionRepository.delete(result);
			return true;
		}
		return false;
	}

	@Override
	public RegionTO edit(RegionTO regionTO, Long id) {
		Region result = regionRepository.findOne(id);
		
		Region restaurantType = conversionService.convert(regionTO, Region.class);
		result.update(restaurantType);
		
		Region resultUpdated = regionRepository.save(result);
		return conversionService.convert(resultUpdated, RegionTO.class);
	}

	@Override
	public RegionTO find(Long id) {
		Region result = regionRepository.findOne(id);
		return conversionService.convert(result, RegionTO.class);
	}

	@Override
	@Cacheable("regionsInCache")
	public List<RegionTO> getAll() {
		List<RegionTO> tos = new ArrayList<>();
		Iterable<Region> itr = regionRepository.findAll();
		for (Region restaurantType : itr) {
			tos.add(conversionService.convert(restaurantType, RegionTO.class));
		}
		return tos;
	}

	@Override
	@Cacheable("regionsInCache")
	public Page<RegionTO> findAllPageable(Pageable pageable) {
		//return examTypeRepository.findAll(pageable);
		return null;
	}
}
