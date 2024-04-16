package com.co.ptq.financiera.application.usecases;

import org.springframework.stereotype.Component;

import com.co.ptq.financiera.application.services.ClienteService;
import com.co.ptq.financiera.domain.models.Cliente;

@Component
public class ObtenerClienteUseCase {

    private final ClienteService clienteService;

    public ObtenerClienteUseCase(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public Cliente obtenerClientePorId(Long id) {
        // Buscar el cliente por ID
        return clienteService.obtenerClientePorId(id);
    }

    // Opcional: Puedes agregar otros métodos para obtener clientes por diferentes criterios, como:
    // public List<Cliente> obtenerClientesPorNombre(String nombre) { ... }
    // public List<Cliente> obtenerClientesPorApellido(String apellido) { ... }
    // etc.
}