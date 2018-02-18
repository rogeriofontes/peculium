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

import br.com.rft.peculium.models.Establishment;
import br.com.rft.peculium.repositories.EstablishmentRepository;
import br.com.rft.peculium.services.EstablishmentService;
import br.com.rft.peculium.web.to.EstablishmentTO;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EstablishmentRepository establishmentRepository;
	
	@Autowired
	private ConversionService conversionService;

	@Override
	public EstablishmentTO save(EstablishmentTO establishmentTO) {
		logger.info("Saved: " + !StringUtils.isEmpty(establishmentTO.toString()));
		Establishment resultSaved = establishmentRepository.save(conversionService.convert(establishmentTO, Establishment.class));
		return conversionService.convert(resultSaved, EstablishmentTO.class);
	}

	@Override
	public Boolean delete(Long id) {
		Establishment result = establishmentRepository.findOne(id);
		if (result != null) {
			establishmentRepository.delete(result);
			return true;
		}
		return false;
	}

	@Override
	public EstablishmentTO edit(EstablishmentTO establishmentTO, Long id) {
		Establishment result = establishmentRepository.findOne(id);
		
		Establishment restaurantType = conversionService.convert(establishmentTO, Establishment.class);
		result.update(restaurantType);
		
		Establishment resultUpdated = establishmentRepository.save(result);
		return conversionService.convert(resultUpdated, EstablishmentTO.class);
	}

	@Override
	public EstablishmentTO find(Long id) {
		Establishment result = establishmentRepository.findOne(id);
		return conversionService.convert(result, EstablishmentTO.class);
	}

	@Override
	@Cacheable("establishmentsInCache")
	public List<EstablishmentTO> getAll() {
		List<EstablishmentTO> tos = new ArrayList<>();
		Iterable<Establishment> itr = establishmentRepository.findAll();
		for (Establishment restaurantType : itr) {
			tos.add(conversionService.convert(restaurantType, EstablishmentTO.class));
		}
		return tos;
	}

	@Override
	@Cacheable("establishmentsInCache")
	public Page<EstablishmentTO> findAllPageable(Pageable pageable) {
		//return examTypeRepository.findAll(pageable);
		return null;
	}

	@Override
	public EstablishmentTO findByName(String name) {
		Establishment result = establishmentRepository.findByName(name);
		return conversionService.convert(result, EstablishmentTO.class);
	}
}
