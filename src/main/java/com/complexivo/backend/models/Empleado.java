package com.complexivo.backend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 
 * @Entity: Anotación para definir una entity o entidad.
 * 
 * @Table: Anotación para definir el nombre de la tabla.
 * 
 * */
@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {
	
	/*
	 * 
	 * @Id: Anotación para definir el ID y clave primaria.
	 * 
	 * @GeneratedValue: Anotación para la estrategia de generacion de la llave primaria.
	 * 
	 * @Column: Anotación para indicar el nombre del campo y mas restricciones.
	 * 
	 * @NotEmpty: Anotacion para indicar que no debe estar vacio el campo.
	 * 
	 * @NotNull: Indica que el campo no debe ser nulo.
	 * 
	 * 
	 * */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Long empId;

	@NotNull
	@NotEmpty
	@Column(name = "emp_cargo", nullable = false)
	private String empCargo;

	@NotNull
	@NotEmpty
	@Column(name = "emp_genero", nullable = false)
	private String empGenero;
	
	
	/* ------------------------------- RELACIONES ------------------------------- */
	/*
	 * @JsonIgnoreProperties: Ignora propiedades que vienen en el JSON.
	 * 
	 * @OneToOne: Define la relacion entre entidades
	 * 
	 * FETCH: define la forma de carga.
	 * 
	 * LAZY: carga perezosa
	 * 
	 * @JoinColumn: Define el nombre de la foreing key.
	 * 
	 * 
	 * */
	
	/* ------------------------------ RELACIONES UNIDIRECCIONALES -----------------------------------*/
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "per_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Persona persona;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dir_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Direccion direccion;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usu_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Usuario usuario;

	// Metodo constructor
	public Empleado() {
		super();
	}

	// Metodos Getters y Setters
	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpCargo() {
		return empCargo;
	}

	public void setEmpCargo(String empCargo) {
		this.empCargo = empCargo;
	}

	public String getEmpGenero() {
		return empGenero;
	}

	public void setEmpGenero(String empGenero) {
		this.empGenero = empGenero;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/* atributo estatico que es requerido por la interfaz serializable */
	private static final long serialVersionUID = 1L;

}
