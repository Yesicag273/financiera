package com.co.ptq.financiera.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.co.ptq.financiera.application.services.ClienteService;
import com.co.ptq.financiera.domain.models.Cliente;
import com.co.ptq.financiera.infrastructure.controllers.entities.CrearClienteRequest;

@ExtendWith(MockitoExtension.class)
public class RegistrarClienteUseCaseTest {

	@Test
	public void testRegistrarCliente() {

		ClienteService clienteServiceMock = mock(ClienteService.class);
		LocalDate fechaNacimiento = LocalDate.of(1995, 03, 27);
		RegistrarClienteUseCase registrarClienteUseCase = new RegistrarClienteUseCase(clienteServiceMock);
		CrearClienteRequest request = new CrearClienteRequest(1L, "cc", "15223", "Yesi", "Agudelo", "yesi@gmail.com",
				fechaNacimiento);

		Cliente clienteRegistradoMock = new Cliente();
		when(clienteServiceMock.registrarCliente(any(Cliente.class))).thenReturn(clienteRegistradoMock);

		Cliente clienteRegistrado = registrarClienteUseCase.registrarCliente(request);

		assertNotNull(clienteRegistrado);
		verify(clienteServiceMock, times(1)).registrarCliente(any(Cliente.class));
	}

	@Test
	public void testRegistrarCliente_NombreVacio() {

		ClienteService clienteServiceMock = mock(ClienteService.class);
		LocalDate fechaNacimiento = LocalDate.of(1995, 03, 27);

		RegistrarClienteUseCase registrarClienteUseCase = new RegistrarClienteUseCase(clienteServiceMock);
		CrearClienteRequest request = new CrearClienteRequest(1L, "cc", "15223", "", "Agudelo", "yesi@gmail.com",
				fechaNacimiento);

		try {
			registrarClienteUseCase.registrarCliente(request);
			fail("Se esperaba que se lanzara una IllegalArgumentException");
		} catch (IllegalArgumentException e) {

			assertEquals("El nombre del cliente es obligatorio.", e.getMessage());
		}
	}

	@Test
	public void testRegistrarCliente_ApellidoVacio() {
		LocalDate fechaNacimiento = LocalDate.of(1995, 03, 27);
		ClienteService clienteServiceMock = mock(ClienteService.class);
		RegistrarClienteUseCase registrarClienteUseCase = new RegistrarClienteUseCase(clienteServiceMock);
		CrearClienteRequest request = new CrearClienteRequest(1L, "cc", "15223", "Yesi", "", "yesi@gmail.com",
				fechaNacimiento);

		try {
			registrarClienteUseCase.registrarCliente(request);
			fail("Se espera una IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("El Apellido del cliente es obligatorio.", e.getMessage());
		}
	}
}