package com.complexivo.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.complexivo.backend.models.Cita;
import com.complexivo.backend.services.ServicioCita;

/*
 * 
 * @CrossOrigin: permite solicitudes HTTP de origen cruzado desde un solo origen.
 * 
 * origins: define desde que origenes se pueden conectar
 * 
 * maxAge: duracion de la memoria cache para las respuestas.
 * 
 * @RestController: Define uncontrolador para simplificar la creacion de un RESTful Web Service
 * 
 * @RequestMapping: Relaciona un metodo con una peticion HTTP 
 * 
 * 
 * */
@CrossOrigin(origins = "**", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RestCita {

	/*
	 * 
	 * @Autowired: Inyeccion de dependencias
	 * 
	 * @GetMapping: Asigna solicitudes HTTP GET(Obtener) al metodo.
	 * 
	 * @PostMapping: Asigna solicitudes HTTP POST(Insertar) al metodo.
	 * 
	 * @PutMapping: Asigna solicitudes HTTP PUT(Actualizar) al metodo.
	 * 
	 * @DeleteMapping: Asigna solicitudes HTTP DELETE(Eliminar) al metodo.
	 * 
	 * @PathVariable: Configurar o enviar variables dentro de la URL.
	 * 
	 * @Valid: Valida las reestricciones definidas en el objeto y sus propiedades.
	 * 
	 * @RequestBody: Datos que van en el cuerpo de la peticion junto con
	 * 				 toda la informacion de la peticion. 
	 * 
	 * BindingResult: Resultados de validacion y contiene los errores 
	 * 				  que pueden haber ocurrido.
	 * 
	 * @PathVariable: Indica que un parametro del metodo debe vincularse a 
	 * 				  una variable de plantilla URL
	 * 
	 * */
	
	@Autowired
	private ServicioCita servicioCita;

	/* -------------------------- METODO PARA LLISTAR TODOS LOS REGISTROS -------------------------- */
	@GetMapping("/citas")
	public ResponseEntity<List<Cita>> all() {
		/* Enviamos la respuesta con mensaje de exito y un status 200 -> OK */
		return ResponseEntity.ok(servicioCita.findAll());
	}

	/* -------------------------- METODO PARA BUSCAR UN REGISTRO POR ID -------------------------- */
	@GetMapping("/citas/{id}")
	public ResponseEntity<?> findId(@PathVariable Long id) {

		Cita cita = null;
		Map<String, Object> response = new HashMap<>();

		/* Captura de errores si no se puede consultar un registro en la Base de Datos */
		try {
			cita = servicioCita.findById(id);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta con mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("Mensaje", "Error al realizar la consulta a la Base de Datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Cuando no encuentra el registro */
		if (cita == null) {
			/* Enviamos la respuesta con un mensaje de error y un status 404 -> NOT_FOUND */
			response.put("mensaje",
					"La cita con ID: '".concat(id.toString()).concat("' no existe en la Base de Datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		/* 
		 * Enviamos la respuesta de existencia del registro con el JSON
		 * y un status 200 -> OK
		 * 
		 *  */
		return ResponseEntity.ok(cita);
	}

	/* -------------------------- METODO PARA INSERTAR UN REGISTRO -------------------------- */
	@PostMapping("/citas")
	public ResponseEntity<?> create(@Valid @RequestBody Cita cita, BindingResult result) {

		Cita citaNew = null;
		Map<String, Object> response = new HashMap<>();

		/* Verificamos si hay errores de validacion en el envio de los datos */
		if (result.hasErrors()) {
			/* Creamos una lista con los mensajes de error de las validaciones */
			List<String> errores = result.getFieldErrors().stream().map((err) -> {
				return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			}).collect(Collectors.toList());

			/* 
			 * Enviamos la respuesta con la lista de mensajes de las validaciones
			 * y un status 400 -> BAD_REQUEST
			 * 
			 *  */
			response.put("errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		/* Captura de errores si no se puede insertar un registro en la Base de Datos */
		try {
			citaNew = servicioCita.save(cita);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta, con un mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("Mensaje", "Error al guardar la cita en la Base de Datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Enviamos la respuesta, con un mensaje de exito y un status 201 -> CREATED */
		response.put("Mensaje", "La cita se ha creado con exito!");
		response.put("Cita", citaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/* -------------------------- METODO PARA ACTUALIZAR UN REGISTRO -------------------------- */
	@PutMapping("/citas/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cita cita, BindingResult result, @PathVariable Long id) {

		Cita citaActual = servicioCita.findById(id);
		Cita citaUpdate = null;
		Map<String, Object> response = new HashMap<>();

		/* Verificamos si hay errores de validacion en el envio de los datos */
		if (result.hasErrors()) {
			/* Creamos una lista con los mensajes de error de las validaciones */
			List<String> errores = result.getFieldErrors().stream().map((err) -> {
				return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
			}).collect(Collectors.toList());

			/* 
			 * Enviamos la respuesta con la lista de mensajes de las validaciones
			 * y un status 400 -> BAD_REQUEST
			 * 
			 *  */
			response.put("Mensaje", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		/* Verificamos si no encuentra el redistro para actualizar */
		if (citaActual == null) {
			/* Enviamos la respuesta con un mensaje de error y un status 400 -> BAD_REQUEST */
			response.put("Mensaje", "Error, no se pudo editar. la cita con ID: "
					.concat(id.toString().concat(" no se encuentra en la Base de Datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		/* Captura de errores si no se puede actualizar un registro en la Base de Datos */
		try {
			citaActual.setCitaDireccion(cita.getCitaDireccion());
			citaActual.setCitaEstado(cita.isCitaEstado());
			citaActual.setCitaFechaFin(cita.getCitaFechaFin());
			citaActual.setCitaFechaInicio(cita.getCitaFechaInicio());
			citaActual.setCabVenta(cita.getCabVenta());

			citaUpdate = servicioCita.save(citaActual);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta, con un mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("Mensaje", "Error al actualizar la cita");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Enviamos la respuesta, con un mensaje de exito y un status 201 -> CREATED */
		response.put("Mensaje", "La cita se actualizo con exito!");
		response.put("Cita", citaUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/* Enviamos la respuesta, con un mensaje de exito y un status 201 -> CREATED */
	@DeleteMapping("/citas/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		/* Captura de errores si no se puede eliminar un registro en la Base de Datos */
		try {
			servicioCita.deleteById(id);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta, con un mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("Mensaje", "Error al eliminar la cita de la Base de Datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Enviamos la respuesta, con un mensaje de exito y un status 200 -> OK */
		response.put("Mensaje", "La cita se elimino con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
