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

import br.com.rft.peculium.models.Movement;
import br.com.rft.peculium.repositories.MovementRepository;
import br.com.rft.peculium.services.MovementService;
import br.com.rft.peculium.web.to.MovementTO;

@Service
public class MovementServiceImpl implements MovementService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MovementRepository movementRepository;
	
	@Autowired
	private ConversionService conversionService;

	@Override
	public MovementTO save(MovementTO movementTO) {
		logger.info("Saved: " + !StringUtils.isEmpty(movementTO.toString()));
		Movement resultSaved = movementRepository.save(conversionService.convert(movementTO, Movement.class));
		return conversionService.convert(resultSaved, MovementTO.class);
	}

	@Override
	public Boolean delete(Long id) {
		Movement result = movementRepository.findOne(id);
		if (result != null) {
			movementRepository.delete(result);
			return true;
		}
		return false;
	}

	@Override
	public MovementTO edit(MovementTO movementTO, Long id) {
		Movement result = movementRepository.findOne(id);
		
		Movement movement = conversionService.convert(movementTO, Movement.class);
		result.update(movement);
		
		Movement resultUpdated = movementRepository.save(result);
		return conversionService.convert(resultUpdated, MovementTO.class);
	}

	@Override
	public MovementTO find(Long id) {
		Movement result = movementRepository.findOne(id);
		return conversionService.convert(result, MovementTO.class);
	}
	
	@Override
	@Cacheable("movementsInCache")
	public List<MovementTO> getAll() {
		List<MovementTO> tos = new ArrayList<>();
		Iterable<Movement> itr = movementRepository.findAll();
		for (Movement movement : itr) {
			tos.add(conversionService.convert(movement, MovementTO.class));
		}
		return tos;
	}

	@Override
	@Cacheable("movementsInCache")
	public Page<MovementTO> findAllPageable(Pageable pageable) {
		//return examTypeRepository.findAll(pageable);
		return null;
	}

}
