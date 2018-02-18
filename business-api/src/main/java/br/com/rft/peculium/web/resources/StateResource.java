package br.com.rft.peculium.web.resources;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.rft.peculium.services.StateService;
import br.com.rft.peculium.web.to.StateTO;

@RestController
@RequestMapping("/states")
public class StateResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StateService stateService;

/**
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLIC	@ApiOperation(notes = "Returns a States  when 0 < ID <= 10.  ID > 10 or non-integers will simulate API error conditions", value = "Find State s by ID", nickname = "getStateById", tags = {
			"State" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Nice!", response = State.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied", response = State.class),
			@ApiResponse(code = 404, message = "Pet not found", response = State.class) })ATION_JSON_VALUE) */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<StateTO>> list() {
		List<StateTO> states = stateService.getAll();
		logger.info("Finded State s: %s", !StringUtils.isEmpty(states.toString()));
		return new ResponseEntity<>(states, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<StateTO> getById(@PathVariable("id") Long id) {
		StateTO state = stateService.find(id);
		return new ResponseEntity<>(state, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "statesInCache", allEntries = true)
	public ResponseEntity<StateTO> add(@RequestBody StateTO state) {
		StateTO saved = stateService.save(state);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "statesInCache", allEntries = true)
	public ResponseEntity<StateTO> update(@PathVariable("id") Long id,
			@RequestBody StateTO state) {
		StateTO saved = stateService.edit(state, id);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@CacheEvict(value = "statesInCache", allEntries = true)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		stateService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
