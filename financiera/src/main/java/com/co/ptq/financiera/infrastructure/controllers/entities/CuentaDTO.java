package com.co.ptq.financiera.infrastructure.controllers.entities;

import java.math.BigDecimal;

import com.co.ptq.financiera.domain.models.EstadoCuenta;
import com.co.ptq.financiera.domain.models.TipoCuenta;

public class CuentaDTO {
	private Long id;
	private TipoCuenta tipoCuenta;
	private String numeroCuenta;
	private EstadoCuenta estado;
	private BigDecimal saldo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public EstadoCuenta getEstado() {
		return estado;
	}

	public void setEstado(EstadoCuenta estado) {
		this.estado = estado;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}