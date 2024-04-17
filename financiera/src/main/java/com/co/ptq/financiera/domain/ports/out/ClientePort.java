package com.co.ptq.financiera.domain.ports.out;

import java.util.List;

import com.co.ptq.financiera.domain.models.Cliente;

public interface ClientePort {
	Cliente guardarCliente(Cliente cliente);

	Cliente actualizarCliente(Cliente cliente);

	void eliminarCliente(Long id);

	Cliente obtenerClientePorId(Long id);

	List<Cliente> consultarClientes();

}
