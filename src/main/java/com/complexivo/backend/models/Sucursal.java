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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
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
@Table(name = "sucursales")

/*
 *  Implementamos la interfaz serializable. Si se trabaja con formularios
 * se guardara en los atributos de la session.
 * 
 * 
 */
public class Sucursal implements Serializable {

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
	 * @Email: Valida que sea un correo válido.
	 * 
	 * */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "suc_id")
	private Long sucId;

	@NotNull
	@NotEmpty
	@Column(name = "suc_nombre", nullable = false)
	private String sucNombre;

	@NotNull
	@NotEmpty
	@Email
	@Column(name = "suc_email", unique = true, nullable = false)
	private String sucEmail;

	@NotNull
	@NotEmpty
	@Column(name = "suc_telefono", unique = true, nullable = false)
	private String sucTelefono;

	@NotNull
	@NotEmpty
	@Column(name = "suc_hora_abre", nullable = false)
	private String sucHoraAbre;

	@NotNull
	@NotEmpty
	@Column(name = "suc_hora_cierra", nullable = false)
	private String sucHoraCierra;

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
	@JoinColumn(name = "epr_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Empresa empresa;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dir_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Direccion direccion;

	// Metodo constructor.
	public Sucursal() {
		super();
	}

	// Metodos Getters y Setters
	public Long getSucId() {
		return sucId;
	}

	public void setSucId(Long sucId) {
		this.sucId = sucId;
	}

	public String getSucNombre() {
		return sucNombre;
	}

	public void setSucNombre(String sucNombre) {
		this.sucNombre = sucNombre;
	}

	public String getSucEmail() {
		return sucEmail;
	}

	public void setSucEmail(String sucEmail) {
		this.sucEmail = sucEmail;
	}

	public String getSucTelefono() {
		return sucTelefono;
	}

	public void setSucTelefono(String sucTelefono) {
		this.sucTelefono = sucTelefono;
	}

	public String getSucHoraAbre() {
		return sucHoraAbre;
	}

	public void setSucHoraAbre(String sucHoraAbre) {
		this.sucHoraAbre = sucHoraAbre;
	}

	public String getSucHoraCierra() {
		return sucHoraCierra;
	}

	public void setSucHoraCierra(String sucHoraCierra) {
		this.sucHoraCierra = sucHoraCierra;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	/* atributo estatico que es requerido por la interfaz serializable */
	private static final long serialVersionUID = 1L;

}
