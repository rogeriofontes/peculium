package br.com.rft.peculium.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "region")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Region extends AudityEntity {

	private static final long serialVersionUID = 4795974382859364322L;

	@Getter
	@Setter
	private String name;
	
	public void update(Region plate) {
		this.name = plate.getName();
	}

}
