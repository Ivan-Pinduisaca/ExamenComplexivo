package com.complexivo.backend.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
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
@Table(name = "cab_ventas")

/*
 *  Implementamos la interfaz serializable. Si se trabaja con formularios
 * se guardara en los atributos de la session.
 * 
 * 
 */
public class CabVenta implements Serializable {
	
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
	@Column(name = "cve_id")
	private Long cveId;

	@NotNull
	@Column(name = "cve_fecha_emision", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date cveFechaEmision;

	@NotEmpty
	@Column(name = "cve_numfactura", nullable = false)
	private String cveNumFactura;

	
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
	 * cascade: Borra los datos en forma de cascada.
	 * 
	 * */
	
	/* ----------------------- RELACION BIDIRECCIONAL ----------------------- */
	@JsonIgnoreProperties({ "cabVentas", "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cli_id")
	private Cliente cliente;

	/* ------------------------- RELACION UNIDIRECCIONAL ---------------------------- */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tpp_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private TipoPago tipoPago;

	/* ------------------------- RELACION BIDIRECCIONAL ----------------------------- */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cve_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<DetVenta> detVentas;

	// Metodo constructor
	public CabVenta() {
		this.detVentas = new ArrayList<>();
	}
	
	// Genera automaticamente la fecha actual al crear un nuevo registro.
	@PrePersist
	public void prePersist() {
		this.cveFechaEmision = new Date();
	}

	public Long getCveId() {
		return cveId;
	}

	public void setCveId(Long cveId) {
		this.cveId = cveId;
	}

	public Date getCveFechaEmision() {
		return cveFechaEmision;
	}

	public void setCveFechaEmision(Date cveFechaEmision) {
		this.cveFechaEmision = cveFechaEmision;
	}

	public String getCveNumFactura() {
		return cveNumFactura;
	}

	public void setCveNumFactura(String cveNumFactura) {
		this.cveNumFactura = cveNumFactura;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoPago getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public List<DetVenta> getDetVentas() {
		return detVentas;
	}

	public void setDetVentas(List<DetVenta> detVentas) {
		this.detVentas = detVentas;
	}

	/* atributo estatico que es requerido por la interfaz serializable */
	private static final long serialVersionUID = 1L;

}
