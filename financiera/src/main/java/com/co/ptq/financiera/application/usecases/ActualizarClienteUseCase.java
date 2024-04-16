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
        // Buscar el cliente existente
        Cliente clienteExistente = clienteService.obtenerClientePorId(id);

        // Actualizar los campos del cliente (puedes agregar validaciones aquí si es necesario)
        clienteExistente.setNombres(clienteActualizado.getNombres());
        clienteExistente.setApellidos(clienteActualizado.getApellidos());
        // ... Actualizar otros campos

        // Llamar al servicio para actualizar el cliente en la base de datos
        return clienteService.actualizarCliente(id, clienteExistente);
    }
}