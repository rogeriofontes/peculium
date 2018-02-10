package br.com.rft.peculium.web.to;

import java.io.Serializable;

import br.com.rft.peculium.models.District;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString(callSuper = true, of = { "id", "name", "street", "complement", "zipcode", "latitude", "longitude", "district" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationTO implements Serializable {

	private static final long serialVersionUID = 8085237062148183046L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String street;
	
	@Getter
	@Setter
	private String complement;

	@Getter
	@Setter
	private String zipcode;

	@Getter
	@Setter
	private double latitude;
	
	@Getter
	@Setter
	private double longitude;
	
	@Getter
	@Setter
	private District district;
	
}
