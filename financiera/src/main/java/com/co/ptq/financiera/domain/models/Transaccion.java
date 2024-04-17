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

@Entity
@Table(name = "transacciones")
public class Transaccion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDestino;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoTransaccion tipoTransaccion;

	@Column(nullable = false)
	private BigDecimal monto;

	@Column(nullable = false)
	private LocalDateTime fecha;

	@ManyToOne
	@JoinColumn(name = "cuenta_origen_id")
	private Cuenta cuentaOrigen;

	@ManyToOne
	@JoinColumn(name = "cuenta_destino_id")
	private Cuenta cuentaDestino;

	public Transaccion(Long id, Long idDestino, TipoTransaccion tipoTransaccion, BigDecimal monto, LocalDateTime fecha,
			Cuenta cuentaOrigen, Cuenta cuentaDestino) {
		this.id = id;
		this.idDestino = idDestino;
		this.tipoTransaccion = tipoTransaccion;
		this.monto = monto;
		this.fecha = fecha;
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
	}

	public Transaccion() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoTransaccion getTipo() {
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

	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	public TipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}

	public Long getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(Long idDestino) {
		this.idDestino = idDestino;
	}

}