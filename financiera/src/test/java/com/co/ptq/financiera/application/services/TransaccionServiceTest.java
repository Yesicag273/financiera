package com.co.ptq.financiera.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.co.ptq.financiera.domain.models.Cuenta;
import com.co.ptq.financiera.domain.models.EstadoCuenta;
import com.co.ptq.financiera.domain.models.TipoTransaccion;
import com.co.ptq.financiera.domain.models.Transaccion;
import com.co.ptq.financiera.domain.ports.out.CuentaPort;
import com.co.ptq.financiera.domain.ports.out.TransaccionPort;
import com.co.ptq.financiera.exceptions.BusinessException;

@ExtendWith(MockitoExtension.class)
public class TransaccionServiceTest {

	@Mock
	private TransaccionPort transaccionPort;

	@Mock
	private CuentaPort cuentaPort;

	private TransaccionService transaccionService;

	@BeforeEach
	public void setUp() {

		transaccionPort = mock(TransaccionPort.class);
		cuentaPort = mock(CuentaPort.class);

		transaccionService = new TransaccionService(transaccionPort, cuentaPort);
		when(cuentaPort.obtenerCuentaPorId(anyLong())).thenReturn(new Cuenta());

	}

	@Test
	public void testCrearConsignacion() {
		Long idCuenta = 1L;
		BigDecimal monto = BigDecimal.TEN;
		Cuenta cuenta = crearCuenta(idCuenta, BigDecimal.ZERO);

		when(cuentaPort.obtenerCuentaPorId(idCuenta)).thenReturn(cuenta);
		when(cuentaPort.actualizarCuenta(any(Cuenta.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Transaccion transaccion = transaccionService.crearConsignacion(idCuenta, monto);

		assertEquals(monto, cuenta.getSaldo());
	}

	@Test
	public void testCrearRetiroSaldoSuficiente() {
		Long idCuenta = 1L;
		BigDecimal monto = BigDecimal.TEN;
		Cuenta cuenta = crearCuenta(idCuenta, monto);

		when(cuentaPort.obtenerCuentaPorId(idCuenta)).thenReturn(cuenta);
		when(cuentaPort.actualizarCuenta(any(Cuenta.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Transaccion transaccion = transaccionService.crearRetiro(idCuenta, monto);

		assertEquals(BigDecimal.ZERO, cuenta.getSaldo());
	}

	@Test
	public void testCrearRetiroSaldoInsuficiente() {
		Long idCuenta = 1L;
		BigDecimal monto = BigDecimal.TEN;
		Cuenta cuenta = crearCuenta(idCuenta, BigDecimal.ONE);

		when(cuentaPort.obtenerCuentaPorId(idCuenta)).thenReturn(cuenta);

		assertThrows(BusinessException.class, () -> transaccionService.crearRetiro(idCuenta, monto));
	}

	@Test
	public void testCrearTransferenciaSaldoInsuficiente() {
		Long idCuentaOrigen = 1L;
		Long idCuentaDestino = 2L;
		BigDecimal monto = BigDecimal.TEN;
		Cuenta cuentaOrigen = crearCuenta(idCuentaOrigen, BigDecimal.ONE);
		Cuenta cuentaDestino = crearCuenta(idCuentaDestino, BigDecimal.ZERO);

		when(cuentaPort.obtenerCuentaPorId(idCuentaOrigen)).thenReturn(cuentaOrigen);
		when(cuentaPort.obtenerCuentaPorId(idCuentaDestino)).thenReturn(cuentaDestino);

		assertThrows(BusinessException.class,
				() -> transaccionService.crearTransferencia(idCuentaOrigen, idCuentaDestino, monto));
	}

	private Cuenta crearCuenta(Long id, BigDecimal saldo) {
		Cuenta cuenta = new Cuenta();
		cuenta.setId(id);
		cuenta.setEstado(EstadoCuenta.ACTIVA);
		cuenta.setSaldo(saldo);
		return cuenta;
	}
}