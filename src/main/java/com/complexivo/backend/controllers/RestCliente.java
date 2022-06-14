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

import com.complexivo.backend.models.Cliente;
import com.complexivo.backend.services.ServicioCliente;

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
public class RestCliente {

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
	private ServicioCliente servicioCliente;

	/* -------------------------- METODO PARA LLISTAR TODOS LOS REGISTROS -------------------------- */
	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> all() {
		/* Enviamos la respuesta con mensaje de exito y un status 200 -> OK */
		return ResponseEntity.ok(servicioCliente.findAll());
	}

	/* -------------------------- METODO PARA BUSCAR UN REGISTRO POR ID -------------------------- */
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> findId(@PathVariable Long id) {

		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();

		/* Captura de errores si no se puede consultar un registro en la Base de Datos */
		try {
			cliente = servicioCliente.findById(id);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta con mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("Mensaje", "Error al realizar la consulta a la Base de Datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Cuando no encuentra el registro */
		if (cliente == null) {
			/* Enviamos la respuesta con un mensaje de error y un status 404 -> NOT_FOUND */
			response.put("mensaje",
					"El cliente con ID: '".concat(id.toString()).concat("' no existe en la Base de Datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		/* 
		 * Enviamos la respuesta de existencia del registro con el JSON
		 * y un status 200 -> OK
		 * 
		 *  */
		return ResponseEntity.ok(cliente);
	}

	/* -------------------------- METODO PARA INSERTAR UN REGISTRO -------------------------- */
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {

		Cliente clienteNew = null;
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
			clienteNew = servicioCliente.save(cliente);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta, con un mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("Mensaje", "Error al guardar el cliente en la Base de Datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Enviamos la respuesta, con un mensaje de exito y un status 201 -> CREATED */
		response.put("Mensaje", "El cliente se ha creado con exito!");
		response.put("Cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/* -------------------------- METODO PARA ACTUALIZAR UN REGISTRO -------------------------- */
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {

		Cliente clienteActual = servicioCliente.findById(id);
		Cliente clienteUpdate = null;
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
		if (clienteActual == null) {
			/* Enviamos la respuesta con un mensaje de error y un status 400 -> BAD_REQUEST */
			response.put("Mensaje", "Error, no se pudo editar. El cliente con ID: "
					.concat(id.toString().concat(" no se encuentra en la Base de Datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		/* Captura de errores si no se puede actualizar un registro en la Base de Datos */
		try {
			clienteActual.setCliFechaNac(cliente.getCliFechaNac());
			clienteActual.setCliGenero(cliente.getCliGenero());
			clienteActual.setPersona(cliente.getPersona());
			clienteActual.setDireccion(cliente.getDireccion());
			clienteActual.setUsuario(cliente.getUsuario());
			clienteActual.setCabVentas(cliente.getCabVentas());

			clienteUpdate = servicioCliente.save(clienteActual);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta, con un mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("Mensaje", "Error al actualizar el cliente");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Enviamos la respuesta, con un mensaje de exito y un status 201 -> CREATED */
		response.put("Mensaje", "El cliente se actualizo con exito!");
		response.put("Cliente", clienteUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/* Enviamos la respuesta, con un mensaje de exito y un status 201 -> CREATED */
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		/* Captura de errores si no se puede eliminar un registro en la Base de Datos */
		try {
			servicioCliente.deleteById(id);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta, con un mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("Mensaje", "Error al eliminar el cliente de la Base de Datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Enviamos la respuesta, con un mensaje de exito y un status 200 -> OK */
		response.put("Mensaje", "El cliente se elimino con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
