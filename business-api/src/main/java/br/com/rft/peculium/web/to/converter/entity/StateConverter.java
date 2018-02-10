package br.com.rft.peculium.web.to.converter.entity;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.State;
import br.com.rft.peculium.web.to.StateTO;

@Component
public class StateConverter implements Converter<StateTO, State> {

	@Override
	public State convert(StateTO source) {
		State target = new State();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setCountry(source.getCountry());
		target.setCreateBy("root@localhost.com"); //TODO buscar dados do Audity
		target.setCreatedDate(new Date()); //TODO buscar dados do Audity
	
		return target;
	}

}
