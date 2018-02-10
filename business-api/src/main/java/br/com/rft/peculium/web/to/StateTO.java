package br.com.rft.peculium.web.to;

import java.io.Serializable;

import br.com.rft.peculium.models.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true, of = { "id", "name", "country" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StateTO  implements Serializable  {

	private static final long serialVersionUID = -4139677022544802742L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private Country country;

}
