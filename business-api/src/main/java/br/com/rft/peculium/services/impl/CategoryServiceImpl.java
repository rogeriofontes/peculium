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

import br.com.rft.peculium.models.Category;
import br.com.rft.peculium.repositories.CategoryRepository;
import br.com.rft.peculium.services.CategoryService;
import br.com.rft.peculium.web.to.CategoryTO;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ConversionService conversionService;

	@Override
	public CategoryTO save(CategoryTO restaurantTypeTO) {
		logger.info("Saved: " + !StringUtils.isEmpty(restaurantTypeTO.toString()));
		Category resultSaved = categoryRepository.save(conversionService.convert(restaurantTypeTO, Category.class));
		return conversionService.convert(resultSaved, CategoryTO.class);
	}

	@Override
	public Boolean delete(Long id) {
		Category result = categoryRepository.findOne(id);
		if (result != null) {
			categoryRepository.delete(result);
			return true;
		}
		return false;
	}

	@Override
	public CategoryTO edit(CategoryTO restaurantTypeTO, Long id) {
		Category result = categoryRepository.findOne(id);
		
		Category restaurantType = conversionService.convert(restaurantTypeTO, Category.class);
		result.update(restaurantType);
		
		Category resultUpdated = categoryRepository.save(result);
		return conversionService.convert(resultUpdated, CategoryTO.class);
	}

	@Override
	public CategoryTO find(Long id) {
		Category result = categoryRepository.findOne(id);
		return conversionService.convert(result, CategoryTO.class);
	}
	
	@Override
	public CategoryTO findByName(String name) {
		Category result = categoryRepository.findByName(name);
		return conversionService.convert(result, CategoryTO.class);
	}

	@Override
	@Cacheable("categorysInCache")
	public List<CategoryTO> getAll() {
		List<CategoryTO> tos = new ArrayList<>();
		Iterable<Category> itr = categoryRepository.findAll();
		for (Category restaurantType : itr) {
			tos.add(conversionService.convert(restaurantType, CategoryTO.class));
		}
		return tos;
	}

	@Override
	@Cacheable("categorysInCache")
	public Page<CategoryTO> findAllPageable(Pageable pageable) {
		//return examTypeRepository.findAll(pageable);
		return null;
	}
}
