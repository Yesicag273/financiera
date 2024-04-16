package com.co.ptq.financiera.domain.ports.in;

import java.util.List;

import com.co.ptq.financiera.domain.models.Cliente;

public interface ClienteUseCase {

	Cliente registrarCliente(Cliente cliente);

	Cliente actualizarCliente(Long id, Cliente cliente);

	void eliminarCliente(Long id);

	Cliente obtenerClientePorId(Long id);

	List<Cliente> obtenerClientes(); 
}