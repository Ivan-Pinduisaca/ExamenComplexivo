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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 
 * @Entity: Anotación para definir una entity o entidad.
 * 
 * @Table: Anotación para definir el nombre de la tabla.
 * 
 * */

@Entity
@Table(name = "cantones")

/*
 *  Implementamos la interfaz serializable. Si se trabaja con formularios
 * se guardara en los atributos de la session.
 * 
 * 
 */
public class Canton implements Serializable {

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
	 * 
	 * */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "can_id")
	private Long canId;

	@NotEmpty
	@Column(name = "can_nombre", length = 200, unique = true, nullable = false)
	private String canNombre;

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
	
	/* -------------------------- RELACIONES UNIDIRECCIONAL -------------------------- */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prov_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Provincia provincia;

	
	// Metodo Constructor
	public Canton() {
		super();
	}

	// Metodos Getters y Setters
	public Long getCanId() {
		return canId;
	}

	public void setCanId(Long canId) {
		this.canId = canId;
	}

	public String getCanNombre() {
		return canNombre;
	}

	public void setCanNombre(String canNombre) {
		this.canNombre = canNombre;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	/* atributo estatico que es requerido por la interfaz serializable */
	private static final long serialVersionUID = 1L;

}
