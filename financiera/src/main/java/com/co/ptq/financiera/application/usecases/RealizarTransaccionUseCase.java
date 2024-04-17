package com.co.ptq.financiera.application.usecases;

import org.springframework.stereotype.Component;

import com.co.ptq.financiera.application.services.TransaccionService;
import com.co.ptq.financiera.domain.models.Transaccion;
import com.co.ptq.financiera.exceptions.BusinessException;
import com.co.ptq.financiera.infrastructure.controllers.entities.TransaccionRequest;

@Component
public class RealizarTransaccionUseCase {

    private final TransaccionService transaccionService;

    public RealizarTransaccionUseCase(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

	public Transaccion realizarTransaccion(TransaccionRequest request) {

        Transaccion transaccion = null;
        switch (request.getTipoTransaccion()) {
            case CONSIGNACION:
                transaccion = transaccionService.crearConsignacion(request.getIdCuenta(), request.getMonto());
                break;
            case RETIRO:
                transaccion = transaccionService.crearRetiro(request.getIdCuenta(), request.getMonto());
                break;
            case TRANSFERENCIA:
                transaccion = transaccionService.crearTransferencia(request.getIdCuenta(), request.getIdCuentaDestino(), request.getMonto());
                break;
            default:
                throw new BusinessException("Tipo de transacción no válido");
        }

        return transaccion;
    }
}