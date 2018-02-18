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

import br.com.rft.peculium.services.DistrictService;
import br.com.rft.peculium.web.to.DistrictTO;

@RestController
@RequestMapping("/districts")
public class DistrictResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DistrictService districtService;

/**
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLIC	@ApiOperation(notes = "Returns a Districts  when 0 < ID <= 10.  ID > 10 or non-integers will simulate API error conditions", value = "Find District s by ID", nickname = "getDistrictById", tags = {
			"District" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Nice!", response = District.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied", response = District.class),
			@ApiResponse(code = 404, message = "Pet not found", response = District.class) })ATION_JSON_VALUE) */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<DistrictTO>> list() {
		List<DistrictTO> districts = districtService.getAll();
		logger.info("Finded District s: %s", !StringUtils.isEmpty(districts.toString()));
		return new ResponseEntity<>(districts, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<DistrictTO> getById(@PathVariable("id") Long id) {
		DistrictTO district = districtService.find(id);
		return new ResponseEntity<>(district, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "districtsInCache", allEntries = true)
	public ResponseEntity<DistrictTO> add(@RequestBody DistrictTO district) {
		DistrictTO saved = districtService.save(district);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "districtsInCache", allEntries = true)
	public ResponseEntity<DistrictTO> update(@PathVariable("id") Long id,
			@RequestBody DistrictTO district) {
		DistrictTO saved = districtService.edit(district, id);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@CacheEvict(value = "districtsInCache", allEntries = true)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		districtService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
