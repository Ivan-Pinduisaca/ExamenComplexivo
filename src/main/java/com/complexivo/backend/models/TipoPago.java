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
@Table(name = "tipo_pago")

/*
 *  Implementamos la interfaz serializable. Si se trabaja con formularios
 * se guardara en los atributos de la session.
 * 
 * 
 */
public class TipoPago implements Serializable {

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
	@Column(name = "tpp_id")
	private Long tppId;

	@NotNull
	@NotEmpty
	@Column(name = "tpp_tipo_pago", unique = true, nullable = false)
	private String tppTipoPago;

	// Metodo constructor.
	public TipoPago() {
		super();
	}

	// Metodos Getters y Setters
	public Long getTppId() {
		return tppId;
	}

	public void setTppId(Long tppId) {
		this.tppId = tppId;
	}

	public String getTppTipoPago() {
		return tppTipoPago;
	}

	public void setTppTipoPago(String tppTipoPago) {
		this.tppTipoPago = tppTipoPago;
	}

	/* atributo estatico que es requerido por la interfaz serializable */
	private static final long serialVersionUID = 1L;

}
