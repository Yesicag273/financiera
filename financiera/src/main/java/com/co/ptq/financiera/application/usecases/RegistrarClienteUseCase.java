package com.co.ptq.financiera.application.usecases;

import org.springframework.stereotype.Component;

import com.co.ptq.financiera.application.services.ClienteService;
import com.co.ptq.financiera.domain.models.Cliente;

@Component
public class RegistrarClienteUseCase {

	private final ClienteService clienteService;

	public RegistrarClienteUseCase(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public Cliente registrarCliente(Cliente cliente) {
		// Validaciones (Opcional: Puedes realizar validaciones adicionales aquí)
		// ...

		Cliente nuevoCliente = clienteService.registrarCliente(cliente);

		return nuevoCliente;
	}
}