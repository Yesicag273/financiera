package com.co.ptq.financiera.domain.ports.in;

import java.util.List;

import com.co.ptq.financiera.domain.models.Cuenta;

public interface CuentaUseCase {
	Cuenta crearCuenta(Long clienteId, Cuenta cuenta);

	Cuenta actualizarCuenta(Long id, Cuenta cuenta);

	void eliminarCuenta(Long id);

	Cuenta obtenerCuentaPorId(Long id);

	List<Cuenta> obtenerCuentasPorCliente(Long clienteId);

	void activarDesactivarCuenta(Long id);

	void cancelarCuenta(Long id);
}