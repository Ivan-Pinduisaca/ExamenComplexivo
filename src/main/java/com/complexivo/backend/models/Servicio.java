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
@Table(name = "servicios")

/*
 *  Implementamos la interfaz serializable. Si se trabaja con formularios
 * se guardara en los atributos de la session.
 * 
 * 
 */
public class Servicio implements Serializable {

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
	 * */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ser_id")
	private Long serId;

	@NotNull
	@NotEmpty
	@Column(name = "ser_nombre", nullable = false, unique = true)
	private String serNombre;

	@NotNull
	@NotEmpty
	@Column(name = "ser_caracteristicas", nullable = false)
	private String serCaracteristicas;
	
	@NotNull
	@Column(name = "ser_precio", nullable = false)
	private Double serPrecio;
	
	@NotNull
	@Column(name = "ser_estado", nullable = false)
	private boolean serEstado;
	
	@Column(name = "ser_imagen")
	private String serImagen;

	// Metodo constructor.
	public Servicio() {
		super();
	}

	// Metodos Getters y Setters
	public Long getSerId() {
		return serId;
	}

	public void setSerId(Long serId) {
		this.serId = serId;
	}

	public String getSerNombre() {
		return serNombre;
	}

	public void setSerNombre(String serNombre) {
		this.serNombre = serNombre;
	}

	public String getSerCaracteristicas() {
		return serCaracteristicas;
	}

	public void setSerCaracteristicas(String serCaracteristicas) {
		this.serCaracteristicas = serCaracteristicas;
	}

	public Double getSerPrecio() {
		return serPrecio;
	}

	public void setSerPrecio(Double serPrecio) {
		this.serPrecio = serPrecio;
	}

	public boolean isSerEstado() {
		return serEstado;
	}

	public void setSerEstado(boolean serEstado) {
		this.serEstado = serEstado;
	}

	public String getSerImagen() {
		return serImagen;
	}

	public void setSerImagen(String serImagen) {
		this.serImagen = serImagen;
	}

	/* atributo estatico que es requerido por la interfaz serializable */
	private static final long serialVersionUID = 1L;

}
