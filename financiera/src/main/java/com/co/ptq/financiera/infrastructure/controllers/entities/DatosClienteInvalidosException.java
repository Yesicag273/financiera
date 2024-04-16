package com.co.ptq.financiera.infrastructure.controllers.entities;

public class DatosClienteInvalidosException extends RuntimeException {
	public DatosClienteInvalidosException(String mensaje) {
		super(mensaje);
	}
}
