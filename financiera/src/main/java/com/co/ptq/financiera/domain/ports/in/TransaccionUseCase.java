package com.co.ptq.financiera.domain.ports.in;

import com.co.ptq.financiera.domain.models.Transaccion;
import com.co.ptq.financiera.infrastructure.controllers.entities.TransaccionRequest;

public interface TransaccionUseCase {
	Transaccion realizarTransaccion(TransaccionRequest request);
}