package com.co.ptq.financiera.infrastructure.adapters;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.co.ptq.financiera.domain.models.Cuenta;
import com.co.ptq.financiera.domain.ports.out.CuentaPort;
import com.co.ptq.financiera.infrastructure.adapters.repositories.CuentaRepository;

@Repository
public class CuentaJpaAdapter implements CuentaPort {

	private final CuentaRepository cuentaRepository;

	public CuentaJpaAdapter(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	@Override
	public Cuenta guardarCuenta(Cuenta cuenta) {
		return cuentaRepository.save(cuenta);
	}

	@Override
	public Cuenta actualizarCuenta(Cuenta cuenta) {
		return cuentaRepository.save(cuenta);
	}

	@Override
	public void eliminarCuenta(Long id) {
		cuentaRepository.deleteById(id);
	}

	@Override
	public Cuenta obtenerCuentaPorId(Long id) {
		return cuentaRepository.findById(id).orElse(null);
	}

	@Override
	public boolean clienteTieneCuentas(Long idCliente) {
		return cuentaRepository.existsByClienteId(idCliente);
	}

	@Override
	public List<Cuenta> cuentasPorCliente(Long clienteId) {
		return cuentaRepository.findByClienteId(clienteId);
	}

}