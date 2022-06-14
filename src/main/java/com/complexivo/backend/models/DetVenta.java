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
@Table(name = "det_ventas")

/*
 *  Implementamos la interfaz serializable. Si se trabaja con formularios
 * se guardara en los atributos de la session.
 * 
 * 
 */
public class DetVenta implements Serializable {

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
	@Column(name = "dve_id")
	private Long dveId;

	@NotNull
	@Column(name = "dve_cantidad", nullable = false)
	private int dveCantidad;

	@NotNull
	@Column(name = "dve_precio_venta", nullable = false)
	private double dvePrecioVenta;

	@NotNull
	@Column(name = "dve_subtotal", nullable = false)
	private double dveSubtotal;

	@NotNull
	@Column(name = "dve_iva", nullable = false)
	private double dveIva;

	@NotNull
	@Column(name = "dve_descuento")
	private double dveDescuento;

	@NotNull
	@Column(name = "dve_subtotal_iva_desc", nullable = false)
	private double dveSubtotalIvaDesc;

	@NotNull
	@Column(name = "dve_total", nullable = false)
	private double dveTotal;

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
	@JoinColumn(name = "pro_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Producto producto;

	
	// Metodo constructor
	public DetVenta() {
		super();
	}

	// Metodos Getters y Setters
	public Long getDveId() {
		return dveId;
	}

	public void setDveId(Long dveId) {
		this.dveId = dveId;
	}

	public int getDveCantidad() {
		return dveCantidad;
	}

	public void setDveCantidad(int dveCantidad) {
		this.dveCantidad = dveCantidad;
	}

	public double getDvePrecioVenta() {
		return dvePrecioVenta;
	}

	public void setDvePrecioVenta(double dvePrecioVenta) {
		this.dvePrecioVenta = dvePrecioVenta;
	}

	public double getDveSubtotal() {
		return dveSubtotal;
	}

	public void setDveSubtotal(double dveSubtotal) {
		this.dveSubtotal = dveSubtotal;
	}

	public double getDveIva() {
		return dveIva;
	}

	public void setDveIva(double dveIva) {
		this.dveIva = dveIva;
	}

	public double getDveDescuento() {
		return dveDescuento;
	}

	public void setDveDescuento(double dveDescuento) {
		this.dveDescuento = dveDescuento;
	}

	public double getDveSubtotalIvaDesc() {
		return dveSubtotalIvaDesc;
	}

	public void setDveSubtotalIvaDesc(double dveSubtotalIvaDesc) {
		this.dveSubtotalIvaDesc = dveSubtotalIvaDesc;
	}

	public double getDveTotal() {
		return dveTotal;
	}

	public void setDveTotal(double dveTotal) {
		this.dveTotal = dveTotal;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/* atributo estatico que es requerido por la interfaz serializable */
	private static final long serialVersionUID = 1L;

}
