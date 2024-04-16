package com.co.ptq.financiera.application.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.co.ptq.financiera.domain.models.Cuenta;
import com.co.ptq.financiera.domain.models.EstadoCuenta;
import com.co.ptq.financiera.domain.models.TipoTransaccion;
import com.co.ptq.financiera.domain.models.Transaccion;
import com.co.ptq.financiera.domain.ports.in.TransaccionUseCase;
import com.co.ptq.financiera.domain.ports.out.CuentaPort;
import com.co.ptq.financiera.domain.ports.out.TransaccionPort;
import com.co.ptq.financiera.exceptions.BusinessException;
import com.co.ptq.financiera.exceptions.ResourceNotFoundException;
import com.co.ptq.financiera.infrastructure.controllers.entities.TransaccionRequest;

@Service
public class TransaccionService implements TransaccionUseCase {

	private final TransaccionPort transaccionPort;
	private final CuentaPort cuentaPort;

	public TransaccionService(TransaccionPort transaccionPort, CuentaPort cuentaPort) {
		this.transaccionPort = transaccionPort;
		this.cuentaPort = cuentaPort;
	}

	@Override
	public Transaccion realizarTransaccion(TransaccionRequest request) {
		Transaccion transaccion = null;
		switch (request.getTipoTransaccion()) {
		case CONSIGNACION:
			transaccion = crearConsignacion(request.getIdCuenta(), request.getMonto());
			break;
		case RETIRO:
			transaccion = crearRetiro(request.getIdCuenta(), request.getMonto());
			break;
		case TRANSFERENCIA:
			transaccion = crearTransferencia(request.getIdCuenta(), request.getIdCuentaDestino(), request.getMonto());
			break;
		default:
			throw new BusinessException("Tipo de transacción no válido");
		}
		return transaccion;
	}

	public Transaccion crearConsignacion(Long idCuenta, BigDecimal monto) {
		Cuenta cuenta = obtenerCuentaValida(idCuenta);
		cuenta.setSaldo(cuenta.getSaldo().add(monto));
		cuentaPort.actualizarCuenta(cuenta);
		return crearTransaccion(cuenta, null, monto, TipoTransaccion.CONSIGNACION);
	}

	public Transaccion crearRetiro(Long idCuenta, BigDecimal monto) {
		Cuenta cuenta = obtenerCuentaValida(idCuenta);
		if (cuenta.getSaldo().compareTo(monto) < 0) {
			throw new BusinessException("Saldo insuficiente");
		}
		cuenta.setSaldo(cuenta.getSaldo().subtract(monto));
		cuentaPort.actualizarCuenta(cuenta);
		return crearTransaccion(cuenta, null, monto, TipoTransaccion.RETIRO);
	}

	public Transaccion crearTransferencia(Long idCuentaOrigen, Long idCuentaDestino, BigDecimal monto) {
		Cuenta cuentaOrigen = obtenerCuentaValida(idCuentaOrigen);
		Cuenta cuentaDestino = obtenerCuentaValida(idCuentaDestino);
		if (cuentaOrigen.getSaldo().compareTo(monto) < 0) {
			throw new BusinessException("Saldo insuficiente en la cuenta de origen");
		}
		cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().subtract(monto));
		cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(monto));
		cuentaPort.actualizarCuenta(cuentaOrigen);
		cuentaPort.actualizarCuenta(cuentaDestino);
		Transaccion transaccion = crearTransaccion(cuentaOrigen, cuentaDestino, monto, TipoTransaccion.TRANSFERENCIA);
		// (Opcional) Crear una transacción de entrada para la cuenta destino
		crearTransaccion(cuentaDestino, cuentaOrigen, monto, TipoTransaccion.TRANSFERENCIA_ENTRANTE);
		return transaccion;
	}

	private Cuenta obtenerCuentaValida(Long idCuenta) {
		Cuenta cuenta = cuentaPort.obtenerCuentaPorId(idCuenta);
		if (cuenta == null) {
			throw new ResourceNotFoundException("Cuenta no encontrada");
		}
		if (cuenta.getEstado() != EstadoCuenta.ACTIVA) {
			throw new BusinessException("La cuenta no está activa");
		}
		return cuenta;
	}

	private Transaccion crearTransaccion(Cuenta cuentaOrigen, Cuenta cuentaDestino, BigDecimal monto,
			TipoTransaccion tipo) {
		Transaccion transaccion = new Transaccion();
		transaccion.setCuentaOrigen(cuentaOrigen);
		transaccion.setCuentaDestino(cuentaDestino);
		transaccion.setMonto(monto);
		transaccion.setTipoTransaccion(tipo);
		return transaccionPort.guardarTransaccion(transaccion);
	}
}
