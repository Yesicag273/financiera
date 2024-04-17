package com.co.ptq.financiera.infrastructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ptq.financiera.application.usecases.CrearCuentaUseCase;
import com.co.ptq.financiera.domain.models.Cuenta;

@RestController
@RequestMapping("/api/clientes/{clienteId}/cuentas")
public class CuentaController {

	private final CrearCuentaUseCase crearCuentaUseCase;

	public CuentaController(CrearCuentaUseCase crearCuentaUseCase) {
		this.crearCuentaUseCase = crearCuentaUseCase;
	}

	@PostMapping
	public ResponseEntity<Cuenta> crearCuenta(@PathVariable Long clienteId, @RequestBody Cuenta cuenta) {
		Cuenta nuevaCuenta = crearCuentaUseCase.crearCuenta(clienteId, cuenta);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCuenta);
	}

}