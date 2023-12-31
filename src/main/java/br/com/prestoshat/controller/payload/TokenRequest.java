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
public class TokenRequest implements Serializable {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -5966013488267819053L;
	
	private String token;
}
