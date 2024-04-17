package com.co.ptq.financiera.infrastructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ptq.financiera.application.usecases.RealizarTransaccionUseCase;
import com.co.ptq.financiera.domain.models.Transaccion;
import com.co.ptq.financiera.exceptions.BusinessException;
import com.co.ptq.financiera.exceptions.ResourceNotFoundException;
import com.co.ptq.financiera.infrastructure.controllers.entities.TransaccionRequest;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

	private final RealizarTransaccionUseCase realizarTransaccionUseCase;

	public TransaccionController(RealizarTransaccionUseCase realizarTransaccionUseCase) {
		this.realizarTransaccionUseCase = realizarTransaccionUseCase;
	}

	@PostMapping
	public ResponseEntity<?> crearTransaccion(@RequestBody TransaccionRequest request) {
		try {
			Transaccion transaccion = realizarTransaccionUseCase.realizarTransaccion(request);
			return ResponseEntity.status(HttpStatus.CREATED).body(transaccion);
		} catch (BusinessException ex) {
			return ResponseEntity.badRequest().body("Error de negocio: " + ex.getMessage());
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recurso no encontrado: " + ex.getMessage());
		}
	}

}