package com.co.ptq.financiera.application.usecases;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.co.ptq.financiera.application.services.TransaccionService;
import com.co.ptq.financiera.domain.models.TipoTransaccion;
import com.co.ptq.financiera.domain.models.Transaccion;
import com.co.ptq.financiera.infrastructure.controllers.entities.TransaccionRequest;

class RealizarTransaccionUseCaseTest {

	@Mock
	TransaccionService transaccionService;

	@InjectMocks
	RealizarTransaccionUseCase realizarTransaccionUseCase;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void realizarConsignacionTest() {
		TransaccionRequest request = new TransaccionRequest(TipoTransaccion.CONSIGNACION, new BigDecimal("100.0"), 1L,
				null);
		Transaccion transaccionEsperada = new Transaccion();
		Mockito.when(transaccionService.crearConsignacion(Mockito.anyLong(), Mockito.any(BigDecimal.class)))
				.thenReturn(transaccionEsperada);

		Transaccion transaccionRealizada = realizarTransaccionUseCase.realizarTransaccion(request);

		Assertions.assertEquals(transaccionEsperada, transaccionRealizada);
	}

	@Test
	void realizarRetiroTest() {
		TransaccionRequest request = new TransaccionRequest(TipoTransaccion.RETIRO, new BigDecimal("100.0"), 1L, null);
		Transaccion transaccionEsperada = new Transaccion();
		Mockito.when(transaccionService.crearRetiro(Mockito.anyLong(), Mockito.any(BigDecimal.class)))
				.thenReturn(transaccionEsperada);

		Transaccion transaccionRealizada = realizarTransaccionUseCase.realizarTransaccion(request);
		Assertions.assertEquals(transaccionEsperada, transaccionRealizada);
	}

	@Test
	void realizarTransferenciaTest() {
		// Aquí se asume que se está transfiriendo de la cuenta 1 a la cuenta 2
		TransaccionRequest request = new TransaccionRequest(TipoTransaccion.TRANSFERENCIA, new BigDecimal("100.0"), 1L,
				2L);
		Transaccion transaccionEsperada = new Transaccion();
		Mockito.when(transaccionService.crearTransferencia(Mockito.anyLong(), Mockito.anyLong(),
				Mockito.any(BigDecimal.class))).thenReturn(transaccionEsperada);

		Transaccion transaccionRealizada = realizarTransaccionUseCase.realizarTransaccion(request);

		Assertions.assertEquals(transaccionEsperada, transaccionRealizada);
	}
}
