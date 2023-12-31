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
public class LoginResponse implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8966181192632870974L;

	private String username;
	private String user;
	private String token;
}
