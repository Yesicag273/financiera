package com.co.ptq.financiera.infrastructure.controllers.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.co.ptq.financiera.domain.models.TipoTransaccion;

public class CrearTransaccionRequest {
	private Long id;
	private TipoTransaccion tipoTransaccion;
	private BigDecimal monto;
	private LocalDateTime fecha;
	private Long idCuentaOrigen;
	private Long idCuentaDestino;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
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

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Long getIdCuentaOrigen() {
		return idCuentaOrigen;
	}

	public void setIdCuentaOrigen(Long idCuentaOrigen) {
		this.idCuentaOrigen = idCuentaOrigen;
	}

	public Long getIdCuentaDestino() {
		return idCuentaDestino;
	}

	public void setIdCuentaDestino(Long idCuentaDestino) {
		this.idCuentaDestino = idCuentaDestino;
	}

}
