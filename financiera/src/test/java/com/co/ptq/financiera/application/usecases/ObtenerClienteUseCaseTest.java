package com.co.ptq.financiera.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.co.ptq.financiera.application.services.ClienteService;
import com.co.ptq.financiera.domain.models.Cliente;
import com.co.ptq.financiera.domain.models.Cuenta;

public class ObtenerClienteUseCaseTest {
	@Mock
	private ClienteService clienteService;

	private ObtenerClienteUseCase obtenerClienteUseCase;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		obtenerClienteUseCase = new ObtenerClienteUseCase(clienteService);
	}

	@Test
	public void testObtenerClientePorId() {

		Long idCliente = 1L;
		String tipoId = "CC";
		String numId = "123456789";
		String nombres = "Yesi";
		String apellidos = "Agudelo";
		String correo = "yesi123@gmail.com";
		LocalDateTime fechaNacimiento = LocalDateTime.now();
		LocalDateTime fechaCreacion = LocalDateTime.now();
		LocalDateTime fechaModificacion = LocalDateTime.now();
		List<Cuenta> cuentas = new ArrayList<>();
		Cliente clienteEsperado = new Cliente(idCliente, tipoId, numId, nombres, apellidos, correo, fechaNacimiento,
				fechaCreacion, fechaModificacion, cuentas);

		when(clienteService.obtenerClientePorId(idCliente)).thenReturn(clienteEsperado);

		Cliente clienteObtenido = obtenerClienteUseCase.obtenerClientePorId(idCliente);

		assertEquals(clienteEsperado, clienteObtenido);
	}
}
