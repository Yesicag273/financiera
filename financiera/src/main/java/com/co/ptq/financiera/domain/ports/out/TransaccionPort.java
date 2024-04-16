package com.co.ptq.financiera.domain.ports.out;

import com.co.ptq.financiera.domain.models.Transaccion;

public interface TransaccionPort {
	Transaccion guardarTransaccion(Transaccion transaccion);
}