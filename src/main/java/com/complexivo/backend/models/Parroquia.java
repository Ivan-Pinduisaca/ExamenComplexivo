package com.complexivo.backend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "parroquias")

/*
 *  Implementamos la interfaz serializable. Si se trabaja con formularios
 * se guardara en los atributos de la session.
 * 
 * 
 */
public class Parroquia implements Serializable {

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
	@Column(name = "parr_id")
	private Long parrId;

	@NotNull
	@NotEmpty
	@Column(name = "parr_nombre", unique = true, nullable = false)
	private String parrNombre;

	/* -------------------------- RELACIONES ---------------------- */
	/*
	 * @JsonIgnoreProperties: Ignora propiedades que vienen en el JSON.
	 * 
	 * @ManyToOne: Define la relacion entre entidades
	 * 
	 * FETCH: define la forma de carga.
	 * 
	 * LAZY: carga perezosa
	 * 
	 * @JoinColumn: Define el nombre de la foreing key.
	 * 
	 * */
	
	/* -------------------------- RELACION UNIDIRECCIONAL -------------------------- */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "can_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Canton canton;

	// Metodo constructor
	public Parroquia() {
		super();
	}

	// Metodos Getters y Setters
	public Long getParrId() {
		return parrId;
	}

	public void setParrId(Long parrId) {
		this.parrId = parrId;
	}

	public String getParrNombre() {
		return parrNombre;
	}

	public void setParrNombre(String parrNombre) {
		this.parrNombre = parrNombre;
	}

	public Canton getCanton() {
		return canton;
	}

	public void setCanton(Canton canton) {
		this.canton = canton;
	}

	/* atributo estatico que es requerido por la interfaz serializable */
	private static final long serialVersionUID = 1L;

}
