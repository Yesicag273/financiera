package com.co.ptq.financiera.domain.ports.out;

import com.co.ptq.financiera.domain.models.Cliente;

public interface ClientePort {
	Cliente guardarCliente(Cliente cliente);

	Cliente actualizarCliente(Cliente cliente);

	void eliminarCliente(Long id);

	Cliente obtenerClientePorId(Long id);

}
