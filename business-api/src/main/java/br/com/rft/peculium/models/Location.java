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
@Table(name = "location")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "street", "complement", "zipcode", "latitude", "longitude", "district" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Location extends AudityEntity {

	private static final long serialVersionUID = 4795974382859364322L;

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
	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district;
	
	public void update(Location location) {
		this.name = location.getName();
		this.street = location.getStreet();
		this.complement = location.getComplement();
		this.zipcode = location.getZipcode();
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
		this.district = location.getDistrict();
	}

}
