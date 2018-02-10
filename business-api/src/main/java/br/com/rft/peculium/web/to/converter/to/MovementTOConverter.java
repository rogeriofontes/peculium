package br.com.rft.peculium.web.to.converter.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.Movement;
import br.com.rft.peculium.web.to.MovementTO;

@Component
public class MovementTOConverter implements Converter<Movement, MovementTO> {

	@Override
	public MovementTO convert(Movement source) {
		MovementTO target = new MovementTO();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setDate(source.getDate());
		target.setValue(source.getValue());
		target.setInterest(source.getInterest());
		target.setTotalValue(source.getTotalValue());
		target.setPortion(source.getPortion());
		target.setPortionTotal(source.getPortionTotal());
		target.setCategory(source.getCategory());
		target.setAccount(source.getAccount());
		target.setEstablishment(source.getEstablishment());

		return target;
	}

}
