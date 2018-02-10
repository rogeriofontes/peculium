package br.com.rft.peculium.web.to.converter.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.State;
import br.com.rft.peculium.web.to.StateTO;

@Component
public class StateTOConverter implements Converter<State, StateTO> {

	@Override
	public StateTO convert(State source) {
		StateTO target = new StateTO();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setCountry(source.getCountry());
	
		return target;
	}

}
