package br.com.rft.peculium.web.to.converter.entity;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.Category;
import br.com.rft.peculium.web.to.CategoryTO;

@Component
public class CategoryConverter implements Converter<CategoryTO, Category> {

	@Override
	public Category convert(CategoryTO source) {
		Category target = new Category();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setCreateBy("root@localhost.com"); //TODO buscar dados do Audity
		target.setCreatedDate(new Date()); //TODO buscar dados do Audity
	
		return target;
	}

}
