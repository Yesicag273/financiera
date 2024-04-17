package com.co.ptq.financiera.application.usecases;

import org.springframework.stereotype.Component;

import com.co.ptq.financiera.application.services.ClienteService;
import com.co.ptq.financiera.domain.models.Cliente;
import com.co.ptq.financiera.infrastructure.controllers.entities.CrearClienteRequest;

@Component
public class RegistrarClienteUseCase {

	private final ClienteService clienteService;

	public RegistrarClienteUseCase(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public Cliente registrarCliente(CrearClienteRequest request) {

		if (request.getNombres() == null || request.getNombres().isEmpty()) {
			throw new IllegalArgumentException("El nombre del cliente es obligatorio.");
		}
		if (request.getApellidos() == null || request.getApellidos().isEmpty()) {
			throw new IllegalArgumentException("El Apellido del cliente es obligatorio.");
		}

		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setNombres(request.getNombres());
		nuevoCliente.setApellidos(request.getApellidos());

		Cliente clienteRegistrado = clienteService.registrarCliente(nuevoCliente);

		return clienteRegistrado;
	}
}