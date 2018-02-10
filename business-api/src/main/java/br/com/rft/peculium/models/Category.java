package br.com.rft.peculium.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "category")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category extends AudityEntity {

	private static final long serialVersionUID = -8522875488962473313L;
	private static final int MAX_LENGTH_TITLE = 200;

	@Getter
	@Setter
	private String name;

	public void update(Category category) {
		requireValidName(name);
		this.name = category.getName();
	}

	private void requireValidName(String name) {
		Validate.notNull(name, "Name cannot be null.");
		Validate.notEmpty(name);
		Validate.isTrue(name.length() <= MAX_LENGTH_TITLE, "The maximum length of the title is <%d> characters.",
				MAX_LENGTH_TITLE);
	}

}
