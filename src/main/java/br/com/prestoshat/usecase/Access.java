package br.com.prestoshat.usecase;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prestoshat.controller.payload.UsuarioRequest;
import br.com.prestoshat.persistence.model.Usuario;
import br.com.prestoshat.persistence.model.constants.Status;
import br.com.prestoshat.resources.exception.BusinessException;
import br.com.prestoshat.resources.exception.DaoException;
import br.com.prestoshat.service.UsuarioService;

@Service
public class Access {

	@Autowired
	private UsuarioService service;
	
	/**
	 * 
	 * @param request
	 * @throws DaoException
	 * @throws BusinessException 
	 */
	public void doCreateAccess(UsuarioRequest request) throws DaoException, BusinessException {
		Optional<Usuario> user = this.service.find(request.getUsuario());
		if(user.isPresent() && user.get().getUsuario().equalsIgnoreCase(request.getUsuario())) {
			throw new BusinessException("Usuário já possui conta de acesso nessa aplicação!");
		}
		this.service.insert(Usuario.builder()
			.usuario(request.getUsuario())
			.nome(request.getNome())
			.senha(request.getSenha())
			.email(request.getEmail())
			.errorCount(0)
			.status(Status.ATIVO.ordinal())
			.dataCriacao(new Date())
			.build());
	}
}