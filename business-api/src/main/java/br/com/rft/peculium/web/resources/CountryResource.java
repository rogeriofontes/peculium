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

import br.com.rft.peculium.services.CountryService;
import br.com.rft.peculium.web.to.CountryTO;

@RestController
@RequestMapping("/countrys")
public class CountryResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CountryService countryService;

/**
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLIC	@ApiOperation(notes = "Returns a Countrys  when 0 < ID <= 10.  ID > 10 or non-integers will simulate API error conditions", value = "Find Country s by ID", nickname = "getCountryById", tags = {
			"Country" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Nice!", response = Country.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied", response = Country.class),
			@ApiResponse(code = 404, message = "Pet not found", response = Country.class) })ATION_JSON_VALUE) */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<CountryTO>> list() {
		List<CountryTO> countrys = countryService.getAll();
		logger.info("Finded Country s: %s", !StringUtils.isEmpty(countrys.toString()));
		return new ResponseEntity<>(countrys, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<CountryTO> getById(@PathVariable("id") Long id) {
		CountryTO country = countryService.find(id);
		return new ResponseEntity<>(country, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "countrysInCache", allEntries = true)
	public ResponseEntity<CountryTO> add(@RequestBody CountryTO country) {
		CountryTO saved = countryService.save(country);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "countrysInCache", allEntries = true)
	public ResponseEntity<CountryTO> update(@PathVariable("id") Long id,
			@RequestBody CountryTO country) {
		CountryTO saved = countryService.edit(country, id);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@CacheEvict(value = "countrysInCache", allEntries = true)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		countryService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
