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
@Table(name = "rol_usuarios")

/*
 *  Implementamos la interfaz serializable. Si se trabaja con formularios
 * se guardara en los atributos de la session.
 * 
 * 
 */
public class RolUsuario implements Serializable {

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
	@Column(name = "rolu_id")
	private Long roluId;

	@NotNull
	@Column(name = "rolu_estado", nullable = false)
	private boolean roluEstado;
	
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
	 * referencedColumnName: cuando la relacion es de muchos a muchos
	 * 						 hace referencia a la primary key de la otra Entity
	 * 
	 * */
	
	/* -------------------------- RELACION UNIDIRECCIONAL -------------------------- */

	@JsonIgnoreProperties({ "roles", "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usu_id", referencedColumnName = "usu_id")
	private Usuario usuario;

	@JsonIgnoreProperties({ "usuarios", "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rol_id", referencedColumnName = "rol_id")
	private Rol rol;

	// Metodo constructor.
	public RolUsuario() {
		super();
	}

	// Metodos Getters y Setters
	public Long getRoluId() {
		return roluId;
	}

	public void setRoluId(Long roluId) {
		this.roluId = roluId;
	}

	public boolean isRoluEstado() {
		return roluEstado;
	}

	public void setRoluEstado(boolean roluEstado) {
		this.roluEstado = roluEstado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	/* atributo estatico que es requerido por la interfaz serializable */
	private static final long serialVersionUID = 1L;

}
