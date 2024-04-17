package com.co.ptq.financiera.domain.ports.in;

import com.co.ptq.financiera.domain.models.Transaccion;

public interface TransaccionUseCase {
	Transaccion realizarTransaccion(Transaccion request);
}