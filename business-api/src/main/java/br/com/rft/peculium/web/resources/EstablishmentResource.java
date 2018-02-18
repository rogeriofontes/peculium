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

import br.com.rft.peculium.services.EstablishmentService;
import br.com.rft.peculium.web.to.EstablishmentTO;

@RestController
@RequestMapping("/establishments")
public class EstablishmentResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EstablishmentService establishmentService;

/**
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLIC	@ApiOperation(notes = "Returns a Establishments  when 0 < ID <= 10.  ID > 10 or non-integers will simulate API error conditions", value = "Find Establishment s by ID", nickname = "getEstablishmentById", tags = {
			"Establishment" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Nice!", response = Establishment.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied", response = Establishment.class),
			@ApiResponse(code = 404, message = "Pet not found", response = Establishment.class) })ATION_JSON_VALUE) */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<EstablishmentTO>> list() {
		List<EstablishmentTO> establishments = establishmentService.getAll();
		logger.info("Finded Establishment s: %s", !StringUtils.isEmpty(establishments.toString()));
		return new ResponseEntity<>(establishments, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<EstablishmentTO> getById(@PathVariable("id") Long id) {
		EstablishmentTO establishment = establishmentService.find(id);
		return new ResponseEntity<>(establishment, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/find-by-name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<EstablishmentTO> getByName(@PathVariable("name") String name) {
		EstablishmentTO plate = establishmentService.findByName(name);
		return new ResponseEntity<>(plate, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "establishmentsInCache", allEntries = true)
	public ResponseEntity<EstablishmentTO> add(@RequestBody EstablishmentTO establishment) {
		EstablishmentTO saved = establishmentService.save(establishment);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "establishmentsInCache", allEntries = true)
	public ResponseEntity<EstablishmentTO> update(@PathVariable("id") Long id,
			@RequestBody EstablishmentTO establishment) {
		EstablishmentTO saved = establishmentService.edit(establishment, id);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@CacheEvict(value = "establishmentsInCache", allEntries = true)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		establishmentService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
