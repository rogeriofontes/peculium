package br.com.rft.peculium.web.to;

import java.io.Serializable;

import br.com.rft.peculium.models.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString(callSuper = true, of = { "id", "name", "location" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstablishmentTO implements Serializable {

	private static final long serialVersionUID = -8522875488962473313L;
	
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private Location location;
	

}
