package com.co.ptq.financiera.application.usecases;

import org.springframework.stereotype.Component;

import com.co.ptq.financiera.application.services.ClienteService;
import com.co.ptq.financiera.exceptions.BusinessException;

@Component
public class EliminarClienteUseCase {

    private final ClienteService clienteService;

    public EliminarClienteUseCase(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void eliminarCliente(Long id) {
        // Verificar si el cliente tiene productos vinculados
        if (clienteService.clienteTieneProductos(id)) {
            throw new BusinessException("No se puede eliminar un cliente con productos vinculados");
        }

        // Eliminar el cliente
        clienteService.eliminarCliente(id);
    }
}	