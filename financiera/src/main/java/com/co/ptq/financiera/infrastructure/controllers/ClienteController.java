package com.co.ptq.financiera.infrastructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.ptq.financiera.application.usecases.ActualizarClienteUseCase;
import com.co.ptq.financiera.application.usecases.EliminarClienteUseCase;
import com.co.ptq.financiera.application.usecases.ObtenerClienteUseCase;
import com.co.ptq.financiera.application.usecases.RegistrarClienteUseCase;
import com.co.ptq.financiera.domain.models.Cliente;
import com.co.ptq.financiera.infrastructure.controllers.entities.CrearClienteRequest;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	private final RegistrarClienteUseCase registrarClienteUseCase;
	private final ActualizarClienteUseCase actualizarClienteUseCase;
	private final EliminarClienteUseCase eliminarClienteUseCase;
	private final ObtenerClienteUseCase obtenerClienteUseCase;

	public ClienteController(RegistrarClienteUseCase registrarClienteUseCase,
			ActualizarClienteUseCase actualizarClienteUseCase, EliminarClienteUseCase eliminarClienteUseCase,
			ObtenerClienteUseCase obtenerClienteUseCase) {
		this.registrarClienteUseCase = registrarClienteUseCase;
		this.actualizarClienteUseCase = actualizarClienteUseCase;
		this.eliminarClienteUseCase = eliminarClienteUseCase;
		this.obtenerClienteUseCase = obtenerClienteUseCase;
	}

	@PostMapping
	public ResponseEntity<Cliente> registrarCliente(@RequestBody CrearClienteRequest cliente) {
		Cliente nuevoCliente = registrarClienteUseCase.registrarCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente clienteActualizado = actualizarClienteUseCase.actualizarCliente(id, cliente);
		return ResponseEntity.ok(clienteActualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
		eliminarClienteUseCase.eliminarCliente(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obtenerCliente(@PathVariable Long id) {
		Cliente cliente = obtenerClienteUseCase.obtenerClientePorId(id);
		return ResponseEntity.ok(cliente);
	}

}