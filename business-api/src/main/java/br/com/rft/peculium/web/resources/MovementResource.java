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

import br.com.rft.peculium.services.MovementService;
import br.com.rft.peculium.web.to.MovementTO;

@RestController
@RequestMapping("/movements")
public class MovementResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MovementService movementService;

/*	@ApiOperation(notes = "Returns a Movements  when 0 < ID <= 10.  ID > 10 or non-integers will simulate API error conditions", value = "Find Movement s by ID", nickname = "getMovementById", tags = {
			"Movement" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Nice!", response = Movement.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied", response = Movement.class),
			@ApiResponse(code = 404, message = "Pet not found", response = Movement.class) })*/
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<MovementTO>> list() {
		List<MovementTO> movements = movementService.getAll();
		logger.info("Finded Movement s: %s", !StringUtils.isEmpty(movements.toString()));
		return new ResponseEntity<>(movements, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<MovementTO> getById(@PathVariable("id") Long id) {
		MovementTO movement = movementService.find(id);
		return new ResponseEntity<>(movement, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "movementsInCache", allEntries = true)
	public ResponseEntity<MovementTO> add(@RequestBody MovementTO movement) {
		MovementTO saved = movementService.save(movement);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "movementsInCache", allEntries = true)
	public ResponseEntity<MovementTO> update(@PathVariable("id") Long id,
			@RequestBody MovementTO movement) {
		MovementTO saved = movementService.edit(movement, id);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@CacheEvict(value = "movementsInCache", allEntries = true)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		movementService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
