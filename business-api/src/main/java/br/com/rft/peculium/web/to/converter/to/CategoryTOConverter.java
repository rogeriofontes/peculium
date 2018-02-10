package br.com.rft.peculium.web.to.converter.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.Category;
import br.com.rft.peculium.web.to.CategoryTO;

@Component
public class CategoryTOConverter implements Converter<Category, CategoryTO> {

	@Override
	public CategoryTO convert(Category source) {
		CategoryTO target = new CategoryTO();

		target.setId(source.getId());
		target.setName(source.getName());
	
		return target;
	}

}
