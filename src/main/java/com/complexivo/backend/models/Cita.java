package com.complexivo.backend.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "citas")

/*
 *  Implementamos la interfaz serializable. Si se trabaja con formularios
 * se guardara en los atributos de la session.
 * 
 * 
 */
public class Cita implements Serializable {

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
	 * @Temporal: Indica el tipo de dato equivalente en la BD a Transformar. DATE de java a DATE de sql.
	 * 
	 * */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cita_id")
	private Long citaId;

	@NotNull
	@Column(name = "cita_fecha_inicio", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date citaFechaInicio;

	@NotNull
	@Column(name = "cita_fecha_fin", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date citaFechaFin;

	@NotNull
	@NotEmpty
	@Column(name = "cita_direccion", nullable = false)
	private String citaDireccion;

	@NotNull
	@Column(name = "cita_estado", nullable = false)
	private boolean citaEstado;

	
	/* -------------------------- RELACIONES ---------------------- */
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
	 * */
	
	/* -------------------------- RELACIONES UNIDIRECCIONAL -------------------------- */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cve_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CabVenta cabVenta;

	// Metodo constructor.
	public Cita() {
		super();
	}

	// Metodos Getters y Setters
	public Long getCitaId() {
		return citaId;
	}

	public void setCitaId(Long citaId) {
		this.citaId = citaId;
	}

	public Date getCitaFechaInicio() {
		return citaFechaInicio;
	}

	public void setCitaFechaInicio(Date citaFechaInicio) {
		this.citaFechaInicio = citaFechaInicio;
	}

	public Date getCitaFechaFin() {
		return citaFechaFin;
	}

	public void setCitaFechaFin(java.util.Date citaFechaFin) {
		this.citaFechaFin = citaFechaFin;
	}

	public String getCitaDireccion() {
		return citaDireccion;
	}

	public void setCitaDireccion(String citaDireccion) {
		this.citaDireccion = citaDireccion;
	}

	public boolean isCitaEstado() {
		return citaEstado;
	}

	public void setCitaEstado(boolean citaEstado) {
		this.citaEstado = citaEstado;
	}

	public CabVenta getCabVenta() {
		return cabVenta;
	}

	public void setCabVenta(CabVenta cabVenta) {
		this.cabVenta = cabVenta;
	}

	/* atributo estatico que es requerido por la interfaz serializable */
	private static final long serialVersionUID = 1L;

}
