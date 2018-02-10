package br.com.rft.peculium.web.to.converter.entity;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.rft.peculium.models.Movement;
import br.com.rft.peculium.web.to.MovementTO;

@Component
public class MovementConverter implements Converter<MovementTO, Movement> {

	@Override
	public Movement convert(MovementTO source) {
		Movement target = new Movement();

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
		target.setCreateBy("root@localhost.com"); //TODO buscar dados do Audity
		target.setCreatedDate(new Date()); //TODO buscar dados do Audity

		return target;
	}

}
