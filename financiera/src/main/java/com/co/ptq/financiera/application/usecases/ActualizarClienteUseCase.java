package com.co.ptq.financiera.application.usecases;

import org.springframework.stereotype.Component;

import com.co.ptq.financiera.application.services.ClienteService;
import com.co.ptq.financiera.domain.models.Cliente;

@Component
public class ActualizarClienteUseCase {

    private final ClienteService clienteService;

    public ActualizarClienteUseCase(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {

    	Cliente clienteExistente = clienteService.obtenerClientePorId(id);

        clienteExistente.setNombres(clienteActualizado.getNombres());
        clienteExistente.setApellidos(clienteActualizado.getApellidos());
        clienteExistente.setCorreo(clienteActualizado.getCorreo());
        clienteExistente.setFechaNacimiento(clienteActualizado.getFechaNacimiento());

        // Llamar al servicio para actualizar el cliente en la base de datos
        return clienteService.actualizarCliente(id, clienteExistente);
    }
}