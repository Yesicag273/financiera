package com.co.ptq.financiera.infrastructure.controllers.entities;

import java.math.BigDecimal;

import com.co.ptq.financiera.domain.models.TipoTransaccion;

public class TransaccionRequest {

	private TipoTransaccion tipoTransaccion;
	private BigDecimal monto;
	private Long idCuenta;
	private Long idCuentaDestino;

	public TipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}

	public TransaccionRequest(TipoTransaccion tipoTransaccion, BigDecimal monto, Long idCuenta, Long idCuentaDestino) {
		this.tipoTransaccion = tipoTransaccion;
		this.monto = monto;
		this.idCuenta = idCuenta;
		this.idCuentaDestino = idCuentaDestino;
	}

	public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Long getIdCuentaDestino() {
		return idCuentaDestino;
	}

	public void setIdCuentaDestino(Long idCuentaDestino) {
		this.idCuentaDestino = idCuentaDestino;
	}

}