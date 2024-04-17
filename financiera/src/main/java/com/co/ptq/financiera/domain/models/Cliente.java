package com.co.ptq.financiera.domain.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import com.co.ptq.financiera.exceptions.BusinessException;

@Entity
@Table(name = "clientes")
public class Cliente {

	public Cliente() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String tipoId;

	@Column(nullable = false, unique = true)
	private String numId;

	@Column(nullable = false)
	private String nombres;

	@Column(nullable = false)
	private String apellidos;

	@Column(nullable = false, unique = true)
	private String correo;

	@Column(nullable = false)
	private LocalDateTime fechaNacimiento;

	@Column(nullable = false)
	private LocalDateTime fechaCreacion;

	@Column(nullable = false)
	private LocalDateTime fechaModificacion;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
	private List<Cuenta> cuentas;

	public Cliente(Long id, String tipoId, String numId, String nombres, String apellidos, String correo,
			LocalDateTime fechaNacimiento, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion,
			List<Cuenta> cuentas) {
		this.id = id;
		this.tipoId = tipoId;
		this.numId = numId;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.cuentas = cuentas;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoId() {
		return tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public String getNumId() {
		return numId;
	}

	public void setNumId(String numId) {
		this.numId = numId;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public LocalDateTime getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
		if (calcularEdad(fechaNacimiento) < 18) {
			throw new BusinessException("El cliente debe ser mayor de edad");
		}
		this.fechaNacimiento = fechaNacimiento;
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

	public void setFechaModificacion(LocalDateTime localDateTime) {
		this.fechaModificacion = localDateTime;
	}

	// Lógica para calcular la edad
	private int calcularEdad(LocalDateTime fechaNacimiento) {
		// ...
		return 0;
	}
}