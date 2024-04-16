package com.co.ptq.financiera.infrastructure.controllers.repositories;

import org.springframework.stereotype.Repository;

import com.co.ptq.financiera.domain.models.Cliente;
import com.co.ptq.financiera.domain.ports.out.ClientePort;

@Repository
public class ClienteRepositoryImpl implements ClientePort {

	@Override
	public Cliente guardarCliente(Cliente cliente) {
		System.out.println("Cliente guardado en la base de datos: " + cliente);
		return null;
	}

}