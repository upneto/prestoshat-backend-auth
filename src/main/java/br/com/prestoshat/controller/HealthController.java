package br.com.prestoshat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {

	@GetMapping
	public ResponseEntity<?> login() {
		return new ResponseEntity<>("<div style='background-color:#55eb1a;' ><h1>Ativo!!</h1></div>", HttpStatus.OK);
	}
}
