package br.com.rft.peculium.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "movement")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "date", "value", "interest", "totalValue", "portion", "portionTotal", "category", "account", "establishment" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movement extends AudityEntity {

	private static final long serialVersionUID = 1816366153871014861L;

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
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "establishment_id")
	private Establishment establishment;
	
	public void update(Movement movement) {
		this.name = movement.getName();
		this.date = movement.getDate();
		this.value = movement.getValue();
		this.interest = movement.getInterest();
		this.totalValue = movement.getTotalValue();
		this.portion = movement.getPortion();
		this.portionTotal = movement.getPortionTotal();
		this.category = movement.getCategory();
		this.account = movement.getAccount();
		this.establishment = movement.getEstablishment();
	}

}
