package br.com.rft.peculium.web.to;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString(callSuper = true, of = { "id", "name" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegionTO  implements Serializable  {

	private static final long serialVersionUID = 912320371884961025L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String name;

}
