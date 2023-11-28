package br.com.prestoshat.usecase;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prestoshat.controller.payload.LoginRequest;
import br.com.prestoshat.controller.payload.LoginResponse;
import br.com.prestoshat.controller.payload.TokenRequest;
import br.com.prestoshat.persistence.model.Usuario;
import br.com.prestoshat.persistence.model.constants.Status;
import br.com.prestoshat.resources.exception.BusinessException;
import br.com.prestoshat.resources.exception.DaoException;
import br.com.prestoshat.resources.exception.LoginException;
import br.com.prestoshat.service.UsuarioService;
import br.com.prestoshat.usecase.security.TokenBuilder;

@Service
public class Login {

	@Autowired
	private UsuarioService service;
	
	/**
	 * Login
	 * @param request
	 * @return
	 * @throws DaoException
	 * @throws BusinessException
	 */
	public LoginResponse doLogin(LoginRequest request) throws DaoException, LoginException {		
		Usuario usuario = this.service.findByLogin(request.getUsername(), request.getPassword());
		if(usuario == null) {	
			this.updateLoginErrorCount(request.getUsername());
			throw new LoginException("Usuário e(ou) Senha inválidos!");
		}
		else if (usuario.convertStatusToEnum().equals(Status.BLOQUEADO)) {
			throw new LoginException("Usuário Bloqueado!");
		}
		else if (usuario.convertStatusToEnum().equals(Status.INATIVO)) {			
			throw new LoginException("Usuário não possui permissão para acessar o sistema!");
		}
		return LoginResponse.builder()
				.username(usuario.getUsername())
				.user(usuario.getUser())
				.token(TokenBuilder.getInstance().getToken(usuario.getUsername())) 
				.build();
	}
	
	/**
	 * Login
	 * @param request
	 * @return
	 * @throws DaoException
	 * @throws BusinessException
	 */
	public void doVerify(TokenRequest request) throws LoginException {		
		TokenBuilder.getInstance().validateToken(request.getToken());
	}
	
	/**
	 * 
	 * @param usuario
	 * @throws DaoException
	 */
	private void updateLoginErrorCount(String usuario) throws DaoException {
		Optional<Usuario> user = this.service.find(usuario);
		if(user.isPresent()) {
			return;
		}	
		Usuario usuarioUp = user.get();
		int errorCount = usuarioUp.getAttempts();
		if(errorCount < 3) {
			usuarioUp.setAttempts(++errorCount);
		} 
		else {
			usuarioUp.setStatus(Status.BLOQUEADO.ordinal());
		}
		usuarioUp.setDateLastAccess(new Date());
		this.service.update(usuarioUp);
	}	
}
