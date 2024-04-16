package com.co.ptq.financiera.infrastructure.controllers.repositories;

import com.co.ptq.financiera.domain.models.Cuenta;

public interface CuentaPort {

	Cuenta save(Cuenta cuenta);

	Object findById(Long id);
}

