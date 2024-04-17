package com.co.ptq.financiera.infrastructure.controllers.entities;

import java.time.LocalDate;

public class CrearClienteRequest {

	private Long id;
	private String tipoId;
	private String numId;
	private String nombres;
	private String apellidos;
	private String correo;
	private LocalDate fechaNacimiento;

	public CrearClienteRequest(Long id, String tipoId, String numId, String nombres, String apellidos, String correo,
			LocalDate fechaNacimiento) {
		this.id = id;
		this.tipoId = tipoId;
		this.numId = numId;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
	}

	public CrearClienteRequest() {
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

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}