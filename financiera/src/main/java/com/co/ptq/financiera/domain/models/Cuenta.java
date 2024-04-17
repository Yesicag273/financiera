
package com.co.ptq.financiera.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import com.co.ptq.financiera.exceptions.BusinessException;

@Entity
@Table(name = "cuentas")
public class Cuenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoCuenta tipoCuenta;

	@Column(nullable = false, unique = true)
	private String numCuenta;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoCuenta estado;

	@Column(nullable = false)
	private BigDecimal saldo;

	@Column(nullable = false)
	private boolean exentaGMF;

	@Column(nullable = false)
	private LocalDateTime fechaCreacion;

	@Column(nullable = false)
	private LocalDateTime fechaModificacion;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	public Cuenta(Long id, TipoCuenta tipoCuenta, String numCuenta, EstadoCuenta estado, BigDecimal saldo,
			boolean exentaGMF, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion, Cliente cliente) {
		super();
		this.id = id;
		this.tipoCuenta = tipoCuenta;
		this.numCuenta = numCuenta;
		this.estado = estado;
		this.saldo = saldo;
		this.exentaGMF = exentaGMF;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.cliente = cliente;
	}

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

	public String getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
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
		if (tipoCuenta == TipoCuenta.AHORRO && saldo.compareTo(BigDecimal.ZERO) < 0) {
			throw new BusinessException("El saldo de la cuenta de ahorro no puede ser negativo");
		}
		this.saldo = saldo;
	}

	public boolean isExentaGMF() {
		return exentaGMF;
	}

	public void setExentaGMF(boolean exentaGMF) {
		this.exentaGMF = exentaGMF;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
