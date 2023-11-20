package br.com.prestoshat.usecase;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prestoshat.controller.payload.RescueRequest;
import br.com.prestoshat.persistence.model.Usuario;
import br.com.prestoshat.persistence.model.constants.Status;
import br.com.prestoshat.resources.exception.BusinessException;
import br.com.prestoshat.resources.exception.DaoException;
import br.com.prestoshat.service.UsuarioService;

@Service
public class Rescue {
	
	@Autowired
	private UsuarioService service;

	/**
	 * 
	 * @param usuario
	 * @throws DaoException
	 * @throws BusinessException 
	 */
	public void doUpdateStatusToActive(RescueRequest usuario) throws DaoException, BusinessException {
		Usuario user = this.service.findByEmail(usuario.getUsuario(), usuario.getEmail());
		if(user == null) {
			throw new BusinessException("Usuário não possui conta nessa aplicação!");
		}
		user.setStatus(Status.ATIVO.ordinal());
		user.setDataAlteracao(new Date());
		user.setErrorCount(0);
		this.service.update(user);
	}
}