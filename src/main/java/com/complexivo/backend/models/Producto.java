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
@Table(name = "productos")

/*
 *  Implementamos la interfaz serializable. Si se trabaja con formularios
 * se guardara en los atributos de la session.
 * 
 * 
 */
public class Producto implements Serializable {

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
	@Column(name = "pro_id")
	private Long proId;

	@NotNull
	@NotEmpty
	@Column(name = "pro_nombre", unique = true, nullable = false)
	private String proNombre;

	@NotNull
	@NotEmpty
	@Column(name = "pro_codigo", unique = true, nullable = false)
	private String proCodigo;

	@NotNull
	@Column(name = "pro_precio", nullable = false)
	private Double proPrecio;

	@NotNull
	@NotEmpty
	@Column(name = "pro_descripcion", nullable = false)
	private String proDescripcion;

	@NotNull
	@Column(name = "pro_stock", nullable = false)
	private Double proStock;

	@Column(name = "pro_imagen")
	private String proImagen;

	/* -------------------------- RELACIONES -------------------------- */
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
	@JoinColumn(name = "ser_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Servicio servicio;

	// Metodo constructor.
	public Producto() {
		super();
	}

	// Metodos Getters y Setters
	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public String getProNombre() {
		return proNombre;
	}

	public void setProNombre(String proNombre) {
		this.proNombre = proNombre;
	}

	public String getProCodigo() {
		return proCodigo;
	}

	public void setProCodigo(String proCodigo) {
		this.proCodigo = proCodigo;
	}

	public Double getProPrecio() {
		return proPrecio;
	}

	public void setProPrecio(Double proPrecio) {
		this.proPrecio = proPrecio;
	}

	public String getProDescripcion() {
		return proDescripcion;
	}

	public void setProDescripcion(String proDescripcion) {
		this.proDescripcion = proDescripcion;
	}

	public Double getProStock() {
		return proStock;
	}

	public void setProStock(Double proStock) {
		this.proStock = proStock;
	}

	public String getProImagen() {
		return proImagen;
	}

	public void setProImagen(String proImagen) {
		this.proImagen = proImagen;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	/* atributo estatico que es requerido por la interfaz serializable */
	private static final long serialVersionUID = 1L;

}
