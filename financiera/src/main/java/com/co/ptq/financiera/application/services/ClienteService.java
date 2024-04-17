package com.co.ptq.financiera.application.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.co.ptq.financiera.domain.models.Cliente;
import com.co.ptq.financiera.domain.ports.in.ClienteUseCase;
import com.co.ptq.financiera.domain.ports.out.ClientePort;
import com.co.ptq.financiera.exceptions.BusinessException;
import com.co.ptq.financiera.exceptions.ResourceNotFoundException;

@Service
public class ClienteService implements ClienteUseCase {

	private final ClientePort clientePort;

	public ClienteService(ClientePort clientePort) {
		this.clientePort = clientePort;
	}

	@Override
	public Cliente registrarCliente(Cliente cliente) {
		validarEdad(cliente.getFechaNacimiento());
		validarFormatoEmail(cliente.getCorreo());
		validarLongitudNombreApellido(cliente.getNombres(), cliente.getApellidos());

		cliente.setFechaCreacion(LocalDateTime.now());
		return clientePort.guardarCliente(cliente);
	}

	@Override
	public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
		Cliente clienteExistente = clientePort.obtenerClientePorId(id);

		if (clienteExistente == null) {
			throw new ResourceNotFoundException("Cliente no encontrado");
		}

		// Actualizar los campos del cliente (puedes agregar validaciones aquí si es
		// necesario)

		clienteExistente.setNombres(clienteActualizado.getNombres());
		clienteExistente.setApellidos(clienteActualizado.getApellidos());
		// ... Actualizar otros campos

		clienteExistente.setFechaModificacion(LocalDateTime.now());
		return clientePort.actualizarCliente(clienteExistente);
	}

	@Override
	public void eliminarCliente(Long id) {
		if (clienteTieneCuentas(id)) {
			throw new BusinessException("No se puede eliminar un cliente con cuentass vinculados");
		}
		clientePort.eliminarCliente(id);
	}

	@Override
	public Cliente obtenerClientePorId(Long id) {
		Cliente cliente = clientePort.obtenerClientePorId(id);
		if (cliente == null) {
			throw new ResourceNotFoundException("Cliente no encontrado");
		}
		return cliente;
	}

	// Métodos de validación
	private void validarEdad(LocalDateTime fechaNacimiento) {
		// Calcular la edad y lanzar una excepción si es menor de edad
	}

	private void validarFormatoEmail(String email) {
		// Validar el formato del email usando expresiones regulares o un validador
	}

	private void validarLongitudNombreApellido(String nombres, String apellido) {
		// Validar la longitud mínima de nombres y apellido
	}

	public boolean clienteTieneCuentas(Long idCliente) {

		return false;
		// Verificar si el cliente tiene cuentas asociados usando el
		// CuentaPort
	}

	///////////////

	@Override
	public List<Cliente> obtenerClientes() {
		// TODO Auto-generated method stub
		return null;
	}

}