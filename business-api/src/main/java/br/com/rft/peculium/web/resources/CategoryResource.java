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

import br.com.rft.peculium.services.CategoryService;
import br.com.rft.peculium.web.to.CategoryTO;

@RestController
@RequestMapping("/categorys")
//@Api("Serviço de Buscar de Pratos para Restaurantes")
public class CategoryResource {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CategoryService categoryService;

	/*@ApiOperation(notes = "Returns a Restaurants Type when 0 < ID <= 10.  ID > 10 or non-integers will simulate API error conditions", value = "Find Restaurant Types by ID", nickname = "getCategoryById", tags = {
			"Category" })
	@ApiResponses({ @ApiResponse(code = 200, message = "Nice!", response = Category.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied", response = Category.class),
			@ApiResponse(code = 404, message = "Pet not found", response = Category.class) })*/
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<CategoryTO>> list() {
		List<CategoryTO> categorys = categoryService.getAll();
		logger.info("Finded Restaurant Types: %s", !StringUtils.isEmpty(categorys.toString()));
		return new ResponseEntity<>(categorys, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<CategoryTO> getById(@PathVariable("id") Long id) {
		CategoryTO category = categoryService.find(id);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "categorysInCache", allEntries = true)
	public ResponseEntity<CategoryTO> add(@RequestBody CategoryTO category) {
		CategoryTO saved = categoryService.save(category);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	@CacheEvict(value = "categorysInCache", allEntries = true)
	public ResponseEntity<CategoryTO> update(@PathVariable("id") Long id,
			@RequestBody CategoryTO category) {
		CategoryTO saved = categoryService.edit(category, id);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/find-by-name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<CategoryTO> getByName(@PathVariable("name") String name) {
		CategoryTO plate = categoryService.findByName(name);
		return new ResponseEntity<>(plate, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@CacheEvict(value = "categorysInCache", allEntries = true)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		categoryService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
