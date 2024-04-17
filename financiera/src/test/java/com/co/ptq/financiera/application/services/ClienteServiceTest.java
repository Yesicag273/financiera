package com.co.ptq.financiera.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.co.ptq.financiera.domain.models.Cliente;
import com.co.ptq.financiera.domain.ports.out.ClientePort;
import com.co.ptq.financiera.exceptions.BusinessException;
import com.co.ptq.financiera.exceptions.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

	@Mock
	private ClientePort clientePort;

	private ClienteService clienteService;

	@BeforeEach
	public void setUp() {
		clienteService = new ClienteService(clientePort);
	}

	@Test
	public void testRegistrarCliente() {
		Cliente cliente = new Cliente();
		LocalDateTime fechaNacimiento = LocalDateTime.of(1995, 03, 27, 23, 50);

		cliente.setNombres("Yesica");
		cliente.setApellidos("Agudelo");
		cliente.setCorreo("yesicag273@gmail.com");
		cliente.setTipoId("CC");
		cliente.setNumId("11284");
		cliente.setFechaNacimiento(fechaNacimiento);

		when(clientePort.guardarCliente(cliente)).thenReturn(cliente);

		Cliente clienteGuardado = clienteService.registrarCliente(cliente);

		assertNotNull(clienteGuardado);
		assertEquals(cliente, clienteGuardado);
		verify(clientePort).guardarCliente(cliente);
	}

	@Test
	public void testRegistrarClienteMenorEdad() {
		Cliente cliente = new Cliente();
		cliente.setFechaNacimiento(LocalDateTime.now()); // Menor de edad

		assertThrows(BusinessException.class, () -> clienteService.registrarCliente(cliente));
		verify(clientePort, never()).guardarCliente(cliente);
	}

	@Test
	public void testRegistrarClienteEmailInvalido() {
		Cliente cliente = new Cliente();
		LocalDateTime fechaNacimiento = LocalDateTime.of(1995, 03, 27, 23, 50);
		cliente.setFechaNacimiento(fechaNacimiento);
		cliente.setCorreo("email_invalido");

		assertThrows(BusinessException.class, () -> clienteService.registrarCliente(cliente));
		verify(clientePort, never()).guardarCliente(cliente);
	}

	@Test
	public void testActualizarCliente() {
		Long idCliente = 1L;
		Cliente clienteExistente = new Cliente();
		clienteExistente.setId(idCliente);
		clienteExistente.setNombres("Yesica");

		Cliente clienteActualizado = new Cliente();
		clienteActualizado.setNombres("Leidy");

		when(clientePort.obtenerClientePorId(idCliente)).thenReturn(clienteExistente);
		when(clientePort.actualizarCliente(any(Cliente.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Cliente clienteResultante = clienteService.actualizarCliente(idCliente, clienteActualizado);

		assertEquals("Leidy", clienteResultante.getNombres());
		verify(clientePort).obtenerClientePorId(idCliente);
		verify(clientePort).actualizarCliente(clienteExistente);
	}

	@Test
	public void testObtenerClientePorId() {
		Long idCliente = 1L;
		Cliente cliente = new Cliente();
		cliente.setId(idCliente);

		when(clientePort.obtenerClientePorId(idCliente)).thenReturn(cliente);

		Cliente clienteObtenido = clienteService.obtenerClientePorId(idCliente);

		assertEquals(cliente, clienteObtenido);
		verify(clientePort).obtenerClientePorId(idCliente);
	}

	@Test
	public void testObtenerClientePorIdNoExistente() {
		Long idCliente = 1L;

		when(clientePort.obtenerClientePorId(idCliente)).thenReturn(null);

		assertThrows(ResourceNotFoundException.class, () -> clienteService.obtenerClientePorId(idCliente));
	}

}
