package com.co.ptq.financiera.application.usecases;

import org.springframework.stereotype.Component;

import com.co.ptq.financiera.application.services.CuentaService;
import com.co.ptq.financiera.domain.models.Cuenta;

@Component
public class CrearCuentaUseCase {

	private final CuentaService cuentaService;

	public CrearCuentaUseCase(CuentaService cuentaService) {
		this.cuentaService = cuentaService;
	}

	public Cuenta crearCuenta(Long clienteId, Cuenta cuenta) {

		Cuenta nuevaCuenta = cuentaService.crearCuenta(clienteId, cuenta);

		return nuevaCuenta;
	}
}