package com.complexivo.backend.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
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
@Table(name = "personas")

/*
 *  Implementamos la interfaz serializable. Si se trabaja con formularios
 * se guardara en los atributos de la session.
 * 
 * 
 */
public class Persona implements Serializable {

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
	@Column(name = "per_id")
	private Long PerId;

	@NotNull
	@NotEmpty
	@Column(name = "per_cedula", unique = true, nullable = false)
	private String perCedula;

	@NotNull
	@NotEmpty
	@Column(name = "per_nombre", length = 100, nullable = false)
	private String perNombre;

	@NotNull
	@NotEmpty
	@Column(name = "per_apellido", length = 100, nullable = false)
	private String perApellido;

	@NotNull
	@Email
	@Column(name = "per_email", nullable = false)
	private String perEmail;

	@NotNull
	@Column(name = "per_telefono", nullable = false)
	private String perTelefono;

	// Metodo constructor
	public Persona() {
		super();
	}

	//Metodos Getters y Setters
	public Long getPerId() {
		return PerId;
	}

	public void setPerId(Long perId) {
		PerId = perId;
	}

	public String getPerCedula() {
		return perCedula;
	}

	public void setPerCedula(String perCedula) {
		this.perCedula = perCedula;
	}

	public String getPerNombre() {
		return perNombre;
	}

	public void setPerNombre(String perNombre) {
		this.perNombre = perNombre;
	}

	public String getPerApellido() {
		return perApellido;
	}

	public void setPerApellido(String perApellido) {
		this.perApellido = perApellido;
	}

	public String getPerEmail() {
		return perEmail;
	}

	public void setPerEmail(String perEmail) {
		this.perEmail = perEmail;
	}

	public String getPerTelefono() {
		return perTelefono;
	}

	public void setPerTelefono(String perTelefono) {
		this.perTelefono = perTelefono;
	}

	/* atributo estatico que es requerido por la interfaz serializable */
	private static final long serialVersionUID = 1L;

}
