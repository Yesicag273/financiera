package com.co.ptq.financiera.infrastructure.adapters;


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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuenta actualizarCuenta(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminarCuenta(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cuenta obtenerCuentaPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

    // ... Implementación de los métodos de la interfaz ProductoPort
}