package br.com.prestoshat.controller.payload;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequest implements Serializable {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -2391924788274820395L;
	
	public String username;	
	public String user;
	public String passworld;
	public String email;

}
