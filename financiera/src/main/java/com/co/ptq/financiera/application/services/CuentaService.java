package com.co.ptq.financiera.application.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.co.ptq.financiera.domain.models.Cliente;
import com.co.ptq.financiera.domain.models.Cuenta;
import com.co.ptq.financiera.domain.models.EstadoCuenta;
import com.co.ptq.financiera.domain.models.TipoCuenta;
import com.co.ptq.financiera.domain.ports.in.CuentaUseCase;
import com.co.ptq.financiera.domain.ports.out.ClientePort;
import com.co.ptq.financiera.domain.ports.out.CuentaPort;
import com.co.ptq.financiera.exceptions.BusinessException;
import com.co.ptq.financiera.exceptions.ResourceNotFoundException;

@Service
public class CuentaService implements CuentaUseCase {

	private final CuentaPort cuentaPort;
	private final ClientePort clientePort;

	public CuentaService(CuentaPort cuentaPort, ClientePort clientePort) {
		this.cuentaPort = cuentaPort;
		this.clientePort = clientePort;
	}

	@Override
	public Cuenta crearCuenta(Long clienteId, Cuenta cuenta) {
		Cliente cliente = clientePort.obtenerClientePorId(clienteId);
		if (cliente == null) {
			throw new ResourceNotFoundException("Cliente no encontrado");
		}

		cuenta.setCliente(cliente);
		cuenta.setNumCuenta(generarNumeroCuenta(cuenta.getTipoCuenta()));
		cuenta.setEstado(EstadoCuenta.ACTIVA); // Por defecto, las cuentas de ahorro se crean activas
		cuenta.setFechaCreacion(LocalDateTime.now());
		return cuentaPort.guardarCuenta(cuenta);
	}

	@Override
	public Cuenta actualizarCuenta(Long id, Cuenta cuentaActualizada) {
		Cuenta cuentaExistente = cuentaPort.obtenerCuentaPorId(id);
		if (cuentaExistente == null) {
			throw new ResourceNotFoundException("Producto no encontrado");
		}

		// Actualizar los campos permitidos del productoExistente
		// (puedes agregar validaciones aquí si es necesario)

		cuentaExistente.setFechaModificacion(LocalDateTime.now());

		return cuentaPort.actualizarCuenta(cuentaExistente);
	}

	@Override
	public void activarDesactivarCuenta(Long id) {
		Cuenta cuenta = cuentaPort.obtenerCuentaPorId(id);
		if (cuenta == null) {
			throw new ResourceNotFoundException("Producto no encontrado");
		}

		if (cuenta.getEstado() == EstadoCuenta.ACTIVA) {
			cuenta.setEstado(EstadoCuenta.INACTIVA);
		} else if (cuenta.getEstado() == EstadoCuenta.INACTIVA) {
			cuenta.setEstado(EstadoCuenta.ACTIVA);
		}

		cuentaPort.actualizarCuenta(cuenta);
	}

	@Override
	public void cancelarCuenta(Long id) {
		Cuenta cuenta = cuentaPort.obtenerCuentaPorId(id);
		if (cuenta == null) {
			throw new ResourceNotFoundException("Cuenta no encontrada");
		}

		BigDecimal saldo = cuenta.getSaldo();

		if (saldo.compareTo(BigDecimal.ZERO) > 0) {
			throw new BusinessException("No se puede cancelar una cuenta con saldo positivo");
		}

		cuenta.setEstado(EstadoCuenta.CANCELADA);
		cuentaPort.actualizarCuenta(cuenta);
	}

	@Override
	public Cuenta obtenerCuentaPorId(Long id) {
		Cuenta cuenta = cuentaPort.obtenerCuentaPorId(id);
		if (cuenta == null) {
			throw new ResourceNotFoundException("Producto no encontrado");
		}
		return cuenta;
	}

	// ... otros métodos para consultar productos

	private String generarNumeroCuenta(TipoCuenta tipoCuenta) {
		String prefijo = (tipoCuenta == TipoCuenta.AHORRO) ? "53" : "33";
		// Lógica para generar 7 dígitos aleatorios
		String digitosAleatorios = "..."; // Implementar la generación de números aleatorios
		return prefijo + digitosAleatorios;
	}
//////////////////////////////////

	@Override
	public void eliminarCuenta(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Cuenta> obtenerCuentasPorCliente(Long clienteId) {
		// TODO Auto-generated method stub
		return null;
	}
}
