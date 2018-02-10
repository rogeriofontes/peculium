package br.com.rft.peculium.web.to;

import java.io.Serializable;

import br.com.rft.peculium.models.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString(callSuper = true, of = { "id", "name", "region" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryTO implements Serializable {

	private static final long serialVersionUID = 4795974382859364322L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private Region region;
	
}
