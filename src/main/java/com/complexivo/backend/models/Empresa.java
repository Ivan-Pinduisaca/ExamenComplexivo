package com.complexivo.backend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 * 
 * @Entity: Anotación para definir una entity o entidad.
 * 
 * @Table: Anotación para definir el nombre de la tabla.
 * 
 * */

@Entity
@Table(name = "empresas")

/*
 *  Implementamos la interfaz serializable. Si se trabaja con formularios
 * se guardara en los atributos de la session.
 * 
 * 
 */
public class Empresa implements Serializable {

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
	@Column(name = "epr_id")
	private Long eprId;

	@NotNull
	@NotEmpty
	@Column(name = "epr_ruc", unique = true, nullable = false)
	private String eprRuc;

	@NotNull
	@NotEmpty
	@Column(name = "epr_nombre", unique = true, nullable = false)
	private String eprNombre;

	@Column(name = "epr_logo")
	private String eprLogo;

	// Metodo constructor
	public Empresa() {
		super();
	}

	// Metodos Getters y Setters
	public Long getEprId() {
		return eprId;
	}

	public void setEprId(Long eprId) {
		this.eprId = eprId;
	}

	public String getEprRuc() {
		return eprRuc;
	}

	public void setEprRuc(String eprRuc) {
		this.eprRuc = eprRuc;
	}

	public String getEprNombre() {
		return eprNombre;
	}

	public void setEprNombre(String eprNombre) {
		this.eprNombre = eprNombre;
	}

	public String getEprLogo() {
		return eprLogo;
	}

	public void setEprLogo(String eprLogo) {
		this.eprLogo = eprLogo;
	}

	/* atributo estatico que es requerido por la interfaz serializable */
	private static final long serialVersionUID = 1L;

}
