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

import br.com.rft.peculium.services.LocationService;
import br.com.rft.peculium.web.to.LocationTO;

@RestController
@RequestMapping("/locations")
public class LocationResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LocationService locationService;

/**
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLIC	@ApiOperation(notes = "Returns a Locations  when 0 < ID <= 10.  ID > 10 or non-integers will simulate API error conditions", value = "Find Location s by ID", nickname = "getLocationById", tags = {
			"Location" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Nice!", response = Location.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied", response = Location.class),
			@ApiResponse(code = 404, message = "Pet not found", response = Location.class) })ATION_JSON_VALUE) */
	@ResponseBody
	@Timed
	public ResponseEntity<List<LocationTO>> list() {
		List<LocationTO> locations = locationService.getAll();
		logger.info("Finded Location s: %s", !StringUtils.isEmpty(locations.toString()));
		return new ResponseEntity<>(locations, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<LocationTO> getById(@PathVariable("id") Long id) {
		LocationTO location = locationService.find(id);
		return new ResponseEntity<>(location, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "locationsInCache", allEntries = true)
	public ResponseEntity<LocationTO> add(@RequestBody LocationTO location) {
		LocationTO saved = locationService.save(location);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "locationsInCache", allEntries = true)
	public ResponseEntity<LocationTO> update(@PathVariable("id") Long id,
			@RequestBody LocationTO location) {
		LocationTO saved = locationService.edit(location, id);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@CacheEvict(value = "locationsInCache", allEntries = true)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		locationService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
