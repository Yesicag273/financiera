package com.co.ptq.financiera.domain.ports.out;

import java.util.List;

import com.co.ptq.financiera.domain.models.Cuenta;

public interface CuentaPort {
	Cuenta guardarCuenta(Cuenta cuenta);

	Cuenta actualizarCuenta(Cuenta cuenta);

	void eliminarCuenta(Long id);

	Cuenta obtenerCuentaPorId(Long id);

	boolean clienteTieneCuentas(Long idCliente);

	List<Cuenta> cuentasPorCliente(Long clienteId);
}
