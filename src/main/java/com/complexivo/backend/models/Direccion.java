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
@Table(name = "direcciones")

/*
 *  Implementamos la interfaz serializable. Si se trabaja con formularios
 * se guardara en los atributos de la session.
 * 
 * 
 */
public class Direccion implements Serializable {

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
	@Column(name = "dir_id")
	private Long dirId;

	@NotNull
	@NotEmpty
	@Column(name = "dir_calle_principal", nullable = false)
	private String dirCallePrincipal;

	@Column(name = "dir_calle_secundaria")
	private String dirCalleSecundaria;

	@Column(name = "dir_referencia")
	private String dirReferencia;
	
	
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
	@JoinColumn(name = "parr_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Parroquia parroquia;

	// Metodo constructor
	public Direccion() {
		super();
	}

	// Metodos Getters y Setters
	public Long getDirId() {
		return dirId;
	}

	public void setDirId(Long dirId) {
		this.dirId = dirId;
	}

	public String getDirCallePrincipal() {
		return dirCallePrincipal;
	}

	public void setDirCallePrincipal(String dirCallePrincipal) {
		this.dirCallePrincipal = dirCallePrincipal;
	}

	public String getDirCalleSecundaria() {
		return dirCalleSecundaria;
	}

	public void setDirCalleSecundaria(String dirCalleSecundaria) {
		this.dirCalleSecundaria = dirCalleSecundaria;
	}

	public String getDirReferencia() {
		return dirReferencia;
	}

	public void setDirReferencia(String dirReferencia) {
		this.dirReferencia = dirReferencia;
	}

	public Parroquia getParroquia() {
		return parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}

	/* atributo estatico que es requerido por la interfaz serializable */
	private static final long serialVersionUID = 1L;

}
