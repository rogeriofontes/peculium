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

import br.com.rft.peculium.services.CityService;
import br.com.rft.peculium.web.to.CityTO;

@RestController
@RequestMapping("/citys")
public class CityResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CityService cityService;

/**
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLIC	@ApiOperation(notes = "Returns a Citys  when 0 < ID <= 10.  ID > 10 or non-integers will simulate API error conditions", value = "Find City s by ID", nickname = "getCityById", tags = {
			"City" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Nice!", response = City.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied", response = City.class),
			@ApiResponse(code = 404, message = "Pet not found", response = City.class) })ATION_JSON_VALUE) */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<CityTO>> list() {
		List<CityTO> citys = cityService.getAll();
		logger.info("Finded City s: %s", !StringUtils.isEmpty(citys.toString()));
		return new ResponseEntity<>(citys, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<CityTO> getById(@PathVariable("id") Long id) {
		CityTO city = cityService.find(id);
		return new ResponseEntity<>(city, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "citysInCache", allEntries = true)
	public ResponseEntity<CityTO> add(@RequestBody CityTO city) {
		CityTO saved = cityService.save(city);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "citysInCache", allEntries = true)
	public ResponseEntity<CityTO> update(@PathVariable("id") Long id,
			@RequestBody CityTO city) {
		CityTO saved = cityService.edit(city, id);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@CacheEvict(value = "citysInCache", allEntries = true)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		cityService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
