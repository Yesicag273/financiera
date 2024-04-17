package com.co.ptq.financiera.application.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.co.ptq.financiera.domain.models.Cliente;
import com.co.ptq.financiera.domain.ports.in.ClienteUseCase;
import com.co.ptq.financiera.domain.ports.out.ClientePort;
import com.co.ptq.financiera.domain.ports.out.CuentaPort;
import com.co.ptq.financiera.exceptions.BusinessException;
import com.co.ptq.financiera.exceptions.ResourceNotFoundException;

@Service
public class ClienteService implements ClienteUseCase {

	private final ClientePort clientePort;
	private final CuentaPort cuentaPort;

	public ClienteService(ClientePort clientePort, CuentaPort cuentaPort) {
		this.clientePort = clientePort;
		this.cuentaPort = cuentaPort;
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

		clienteExistente.setNombres(clienteActualizado.getNombres());
		clienteExistente.setApellidos(clienteActualizado.getApellidos());
		clienteExistente.setCorreo(clienteActualizado.getCorreo());

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

	private void validarEdad(LocalDateTime fechaNacimiento) {
		LocalDate fechaActual = LocalDate.now();

		int edad = Period.between(fechaNacimiento.toLocalDate(), fechaActual).getYears();
		if (edad < 18) {
			throw new IllegalArgumentException("El usuario debe ser mayor de edad.");
		}
	}

	private void validarFormatoEmail(String email) {
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(email);

		// Verificar si el email coincide con el formato esperado
		if (!matcher.matches()) {
			throw new IllegalArgumentException("El formato del correo electrónico no es válido.");
		}
	}

	private void validarLongitudNombreApellido(String nombres, String apellido) {
		// Longitud mínima permitida para nombres y apellido
		int longitudMinima = 2;

		// Validar longitud mínima para nombres
		if (nombres.length() < longitudMinima) {
			throw new IllegalArgumentException(
					"La longitud de los nombres debe ser al menos " + longitudMinima + " caracteres.");
		}

		// Validar longitud mínima para apellido
		if (apellido.length() < longitudMinima) {
			throw new IllegalArgumentException(
					"La longitud del apellido debe ser al menos " + longitudMinima + " caracteres.");
		}
	}

	public boolean clienteTieneCuentas(Long idCliente) {
		return cuentaPort.clienteTieneCuentas(idCliente);
	}

	@Override
	public List<Cliente> obtenerClientes() {
		return clientePort.consultarClientes(); 
	}

}