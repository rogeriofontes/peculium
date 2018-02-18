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

import br.com.rft.peculium.services.RegionService;
import br.com.rft.peculium.web.to.RegionTO;

@RestController
@RequestMapping("/regions")
public class RegionResource {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RegionService regionService;

/**
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLIC	@ApiOperation(notes = "Returns a Regions  when 0 < ID <= 10.  ID > 10 or non-integers will simulate API error conditions", value = "Find Region s by ID", nickname = "getRegionById", tags = {
			"Region" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Nice!", response = Region.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied", response = Region.class),
			@ApiResponse(code = 404, message = "Pet not found", response = Region.class) })ATION_JSON_VALUE) */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<RegionTO>> list() {
		List<RegionTO> regions = regionService.getAll();
		logger.info("Finded Region s: %s", !StringUtils.isEmpty(regions.toString()));
		return new ResponseEntity<>(regions, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<RegionTO> getById(@PathVariable("id") Long id) {
		RegionTO region = regionService.find(id);
		return new ResponseEntity<>(region, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "regionsInCache", allEntries = true)
	public ResponseEntity<RegionTO> add(@RequestBody RegionTO region) {
		RegionTO saved = regionService.save(region);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "regionsInCache", allEntries = true)
	public ResponseEntity<RegionTO> update(@PathVariable("id") Long id,
			@RequestBody RegionTO region) {
		RegionTO saved = regionService.edit(region, id);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@CacheEvict(value = "regionsInCache", allEntries = true)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		regionService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
