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
import javax.persistence.OneToMany;
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
@Table(name = "clientes")

/*
 *  Implementamos la interfaz serializable. Si se trabaja con formularios
 * se guardara en los atributos de la session.
 * 
 * 
 */
public class Cliente implements Serializable {

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
	@Column(name = "cli_id")
	private Long cliId;

	@NotNull
	@Column(name = "cli_fecha_nac", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date cliFechaNac;

	@NotEmpty
	@Column(name = "cli_genero", nullable = false)
	private String cliGenero;

	/* -------------------------- RELACIONES -------------------------- */
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
	 * mappedBy: Indica el campo de la entidad dueña de la relacion
	 * 
	 * cascade: Borra los datos en forma de cascada.
	 * 
	 * */
	
	/* -------------------------- RELACIONES UNIDIRECCIONAL -------------------------- */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "per_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Persona persona;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dir_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Direccion direccion;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usu_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Usuario usuario;

	/* -------------------------- RELACION BIDIRECCIONAL -------------------------- */
	@JsonIgnoreProperties({ "cliente", "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<CabVenta> cabVentas;

	// Metodo constructor.
	public Cliente() {
		this.cabVentas = new ArrayList<>();
	}

	// Metodos Getters y Setters
	public Long getCliId() {
		return cliId;
	}

	public void setCliId(Long cliId) {
		this.cliId = cliId;
	}

	public Date getCliFechaNac() {
		return cliFechaNac;
	}

	public void setCliFechaNac(Date cliFechaNac) {
		this.cliFechaNac = cliFechaNac;
	}

	public String getCliGenero() {
		return cliGenero;
	}

	public void setCliGenero(String cliGenero) {
		this.cliGenero = cliGenero;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<CabVenta> getCabVentas() {
		return cabVentas;
	}

	public void setCabVentas(List<CabVenta> cabVentas) {
		this.cabVentas = cabVentas;
	}

	/* atributo estatico que es requerido por la interfaz serializable */
	private static final long serialVersionUID = 1L;

}
