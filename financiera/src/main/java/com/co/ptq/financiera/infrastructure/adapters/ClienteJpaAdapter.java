package com.co.ptq.financiera.infrastructure.adapters;

import org.springframework.stereotype.Repository;

import com.co.ptq.financiera.domain.models.Cliente;
import com.co.ptq.financiera.domain.ports.out.ClientePort;
import com.co.ptq.financiera.infrastructure.adapters.repositories.ClienteRepository;

@Repository
public class ClienteJpaAdapter implements ClientePort {

	private final ClienteRepository clienteRepository;

	public ClienteJpaAdapter(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	public Cliente guardarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente actualizarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public void eliminarCliente(Long id) {
		clienteRepository.deleteById(id);
	}

	@Override
	public Cliente obtenerClientePorId(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

}
