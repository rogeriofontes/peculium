package br.com.rft.peculium.services;

import br.com.rft.peculium.web.to.CategoryTO;

public interface CategoryService extends CrudService<CategoryTO, Long> {

	CategoryTO findByName(String name);
}
