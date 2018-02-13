package br.com.rft.peculium.web.to;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true, of = { "username", "password", "userType" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserTO implements Serializable {
	private static final long serialVersionUID = -4435625289563703924L;

	@Getter
	@Setter
	private String username;
	
	@Getter
	@Setter
	private String password;
	
	@Getter
	@Setter
	private String userType;
}
