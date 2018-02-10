package br.com.rft.peculium.web.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.rft.peculium.models.Account;
import br.com.rft.peculium.models.Category;
import br.com.rft.peculium.models.Establishment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString(callSuper = true, of = { "id", "name", "date", "value", "interest", "totalValue", "portion", "portionTotal",
		"category", "account", "establishment" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovementTO implements Serializable {

	private static final long serialVersionUID = -3278976901478343953L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Getter
	@Setter
	private BigDecimal value;
	
	@Getter
	@Setter
	private int interest;
	
	@Getter
	@Setter
	private BigDecimal totalValue;
	
	@Getter
	@Setter
	private int portion;
	
	@Getter
	@Setter
	private int portionTotal;
	
	@Getter
	@Setter
	private Category category;
	
	@Getter
	@Setter
	private Account account;
	
	@Getter
	@Setter
	private Establishment establishment;

}
