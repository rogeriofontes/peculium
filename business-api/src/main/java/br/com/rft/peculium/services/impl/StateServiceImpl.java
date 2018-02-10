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

import br.com.rft.peculium.models.State;
import br.com.rft.peculium.repositories.StateRepository;
import br.com.rft.peculium.services.StateService;
import br.com.rft.peculium.web.to.StateTO;

@Service
public class StateServiceImpl implements StateService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private ConversionService conversionService;

	@Override
	public StateTO save(StateTO stateTO) {
		logger.info("Saved: " + !StringUtils.isEmpty(stateTO.toString()));
		State resultSaved = stateRepository.save(conversionService.convert(stateTO, State.class));
		return conversionService.convert(resultSaved, StateTO.class);
	}

	@Override
	public Boolean delete(Long id) {
		State result = stateRepository.findOne(id);
		if (result != null) {
			stateRepository.delete(result);
			return true;
		}
		return false;
	}

	@Override
	public StateTO edit(StateTO stateTO, Long id) {
		State result = stateRepository.findOne(id);
		
		State restaurantType = conversionService.convert(stateTO, State.class);
		result.update(restaurantType);
		
		State resultUpdated = stateRepository.save(result);
		return conversionService.convert(resultUpdated, StateTO.class);
	}

	@Override
	public StateTO find(Long id) {
		State result = stateRepository.findOne(id);
		return conversionService.convert(result, StateTO.class);
	}

	@Override
	@Cacheable("statesInCache")
	public List<StateTO> getAll() {
		List<StateTO> tos = new ArrayList<>();
		Iterable<State> itr = stateRepository.findAll();
		for (State restaurantType : itr) {
			tos.add(conversionService.convert(restaurantType, StateTO.class));
		}
		return tos;
	}

	@Override
	@Cacheable("statesInCache")
	public Page<StateTO> findAllPageable(Pageable pageable) {
		//return examTypeRepository.findAll(pageable);
		return null;
	}
}
