package br.com.prestoshat.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prestoshat.controller.payload.RescueRequest;
import br.com.prestoshat.resources.exception.BusinessException;
import br.com.prestoshat.resources.exception.DaoException;
import br.com.prestoshat.usecase.Rescue;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/rescue")
public class RescueController {

	@Autowired
	private Rescue rescueService;
	
	@ApiOperation(value = "Recupera acesso")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recuperou acesso com com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema"),
            @ApiResponse(code = 403, message = "Foi gerada uma exceção de negocio"),
            @ApiResponse(code = 401, message = "Foi gerada uma exceção de autenticação")
    })
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<?> rescueAccess(@RequestBody RescueRequest request) throws DaoException, BusinessException {
		this.rescueService.doUpdateStatusToActive(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
