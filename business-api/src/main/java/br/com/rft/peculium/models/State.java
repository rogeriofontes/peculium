package br.com.rft.peculium.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "state")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "country" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class State extends AudityEntity {

	private static final long serialVersionUID = 4795974382859364322L;

	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;
	
	public void update(State state) {
		this.name = state.getName();
		this.country = state.getCountry();
	}

}
