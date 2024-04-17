package com.co.ptq.financiera.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.co.ptq.financiera.domain.models.Cliente;
import com.co.ptq.financiera.domain.models.Cuenta;
import com.co.ptq.financiera.domain.models.EstadoCuenta;
import com.co.ptq.financiera.domain.models.TipoCuenta;
import com.co.ptq.financiera.domain.ports.out.ClientePort;
import com.co.ptq.financiera.domain.ports.out.CuentaPort;
import com.co.ptq.financiera.exceptions.BusinessException;

@ExtendWith(MockitoExtension.class)
public class CuentaServiceTest {

	@Mock
	private CuentaPort CuentaPort;

	@Mock
	private ClientePort clientePort;

	private CuentaService cuentaService;

	@BeforeEach
	public void setUp() {
		cuentaService = new CuentaService(CuentaPort, clientePort);
	}

	@Test
	public void testCrearCuenta() {
		Long idCliente = 1L;
		Cliente cliente = new Cliente();

		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuenta(TipoCuenta.AHORRO);
		cuenta.setId(1L);

		when(clientePort.obtenerClientePorId(idCliente)).thenReturn(cliente);
		when(CuentaPort.guardarCuenta(any(Cuenta.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Cuenta cuentaCreada = cuentaService.crearCuenta(idCliente, cuenta);

		assertNotNull(cuentaCreada.getId());
		assertEquals(cliente, cuentaCreada.getCliente());
		assertEquals(EstadoCuenta.ACTIVA, cuentaCreada.getEstado());
		assertNotNull(cuentaCreada.getNumCuenta());
		assertTrue(cuentaCreada.getNumCuenta().startsWith("53"));
	}

	@Test
	public void testActivarDesactivarCuenta() {
		Long idCuenta = 1L;
		Cuenta Cuenta = new Cuenta();
		Cuenta.setId(idCuenta);
		Cuenta.setEstado(EstadoCuenta.ACTIVA);

		when(CuentaPort.obtenerCuentaPorId(idCuenta)).thenReturn(Cuenta);
		when(CuentaPort.actualizarCuenta(any(Cuenta.class))).thenAnswer(invocation -> invocation.getArgument(0));

		cuentaService.activarDesactivarCuenta(idCuenta);
		assertEquals(EstadoCuenta.INACTIVA, Cuenta.getEstado());

		cuentaService.activarDesactivarCuenta(idCuenta);
		assertEquals(EstadoCuenta.ACTIVA, Cuenta.getEstado());
	}

	@Test
	public void testCancelarCuentaSaldoCero() {
		Long idCuenta = 1L;
		Cuenta Cuenta = new Cuenta();
		Cuenta.setId(idCuenta);
		Cuenta.setSaldo(BigDecimal.ZERO);

		when(CuentaPort.obtenerCuentaPorId(idCuenta)).thenReturn(Cuenta);
		when(CuentaPort.actualizarCuenta(any(Cuenta.class))).thenAnswer(invocation -> invocation.getArgument(0));

		cuentaService.cancelarCuenta(idCuenta);

		assertEquals(EstadoCuenta.CANCELADA, Cuenta.getEstado());
	}

	@Test
	public void testCancelarCuentaSaldoPositivo() {
		Long idCuenta = 1L;
		Cuenta Cuenta = new Cuenta();
		Cuenta.setId(idCuenta);
		Cuenta.setSaldo(BigDecimal.ONE);

		when(CuentaPort.obtenerCuentaPorId(idCuenta)).thenReturn(Cuenta);

		assertThrows(BusinessException.class, () -> cuentaService.cancelarCuenta(idCuenta));
	}

}
