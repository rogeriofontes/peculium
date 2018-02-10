package br.com.rft.peculium.web.to;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.commons.lang3.Validate;

import br.com.rft.peculium.models.YearMonth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString(callSuper = true, of = { "id", "name", "month" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountTO implements Serializable {

	private static final long serialVersionUID = -8522875488962473313L;
	private static final int MAX_LENGTH_TITLE = 200;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	@Enumerated(EnumType.STRING)
	private YearMonth month;
	
	@Getter
	@Setter
	private BigDecimal balance;

	public void update(AccountTO account) {
		requireValidName(name);
		this.name = account.getName();
		this.month = account.getMonth();
		this.balance = account.getBalance();
	}

	private void requireValidName(String name) {
		Validate.notNull(name, "Name cannot be null.");
		Validate.notEmpty(name);
		Validate.isTrue(name.length() <= MAX_LENGTH_TITLE, "The maximum length of the title is <%d> characters.",
				MAX_LENGTH_TITLE);
	}

}
