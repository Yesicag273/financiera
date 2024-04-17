package com.co.ptq.financiera.infrastructure.adapters;


import org.springframework.stereotype.Repository;

import com.co.ptq.financiera.domain.models.Transaccion;
import com.co.ptq.financiera.domain.ports.out.TransaccionPort;

@Repository
public class TransaccionJpaAdapter implements TransaccionPort {

    private final TransaccionRepository transaccionRepository;

    public TransaccionJpaAdapter(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
    }

	@Override
	public Transaccion guardarTransaccion(Transaccion transaccion) {
		// TODO Auto-generated method stub
		return null;
	}

    // ... Implementación de los métodos de la interfaz TransaccionPort
}