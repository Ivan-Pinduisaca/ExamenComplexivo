package com.complexivo.backend.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.complexivo.backend.models.Servicio;
import com.complexivo.backend.services.ServicioServicio;

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
public class RestServicio {

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
	private ServicioServicio servicioServicio;

	/* -------------------------- METODO PARA LLISTAR TODOS LOS REGISTROS -------------------------- */
	@GetMapping("/servicios")
	public ResponseEntity<List<Servicio>> all() {
		/* Enviamos la respuesta con mensaje de exito y un status 200 -> OK */
		return ResponseEntity.ok(servicioServicio.findAll());
	}

	/* -------------------------- METODO PARA BUSCAR UN REGISTRO POR ID -------------------------- */
	@GetMapping("/servicios/{id}")
	public ResponseEntity<?> findId(@PathVariable Long id) {

		Servicio servicio = null;
		Map<String, Object> response = new HashMap<>();

		/* Captura de errores si no se puede consultar un registro en la Base de Datos */
		try {
			servicio = servicioServicio.findById(id);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta con mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("Mensaje", "Error al realizar la consulta a la Base de Datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Cuando no encuentra el registro */
		if (servicio == null) {
			/* Enviamos la respuesta con un mensaje de error y un status 404 -> NOT_FOUND */
			response.put("mensaje",
					"El servicio con ID: ".concat(id.toString().concat(" no existe en la Base de Datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		/* 
		 * Enviamos la respuesta de existencia del registro con el JSON
		 * y un status 200 -> OK
		 * 
		 *  */
		return ResponseEntity.ok(servicio);
	}

	/* -------------------------- METODO PARA INSERTAR UN REGISTRO -------------------------- */
	@PostMapping("/servicios")
	public ResponseEntity<?> createService(@Valid @RequestBody Servicio servicio, BindingResult result) {

		Servicio servicioNew = null;
		Map<String, Object> response = new HashMap<>();

		/* Verificamos si hay errores de validacion en el envio de los datos */
		if (result.hasErrors()) {
			/* Creamos una lista con los mensajes de error de las validaciones */
			List<String> errores = new ArrayList<>();
			
			for (FieldError err : result.getFieldErrors()) {
				errores.add("El campo '" + err.getField() + "' " + err.getDefaultMessage());
			}

			/* 
			 * Enviamos la respuesta con la lista de mensajes de las validaciones
			 * y un status 400 -> BAD_REQUEST
			 * 
			 *  */
			response.put("Errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		/* Captura de errores si no se puede insertar un registro en la Base de Datos */
		try {
			servicioNew = servicioServicio.save(servicio);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta, con un mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("Mensaje", "Error al guardar el servicio en la Base de Datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Enviamos la respuesta, con un mensaje de exito y un status 201 -> CREATED */
		response.put("mensaje", "El servicio se ha creado con exito!");
		response.put("Servicio", servicioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/* -------------------------- METODO PARA ACTUALIZAR UN REGISTRO -------------------------- */
	@PutMapping("/servicios/{id}")
	public ResponseEntity<?> updateService(@Valid @RequestBody Servicio servicio, BindingResult result, @PathVariable Long id) {

		Servicio servicioActual = servicioServicio.findById(id);
		Servicio servicioUpdate = null;
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
			response.put("Errores", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		/* Verificamos si no encuentra el redistro para actualizar */
		if (servicioActual == null) {
			/* Enviamos la respuesta con un mensaje de error y un status 400 -> BAD_REQUEST */
			response.put("mensaje", "Error, no se pudo editar. El servicio con ID: "
					.concat(id.toString().concat(" no existe en la Base de Datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		/* Captura de errores si no se puede actualizar un registro en la Base de Datos */
		try {
			servicioActual.setSerCaracteristicas(servicio.getSerCaracteristicas());
			servicioActual.setSerPrecio(servicio.getSerPrecio());
			servicioActual.setSerNombre(servicio.getSerNombre());
			servicioActual.setSerImagen(servicio.getSerImagen());
			servicioActual.setSerEstado(servicio.isSerEstado());

			servicioUpdate = servicioServicio.save(servicioActual);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta, con un mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("Mensaje", "Error al actualizar en la Base de Datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Enviamos la respuesta, con un mensaje de exito y un status 201 -> CREATED */
		response.put("mensaje", "El servicio se ha actualizado con exito!");
		response.put("Servicio", servicioUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/* -------------------------- METODO PARA BORRAR UN REGISTRO -------------------------- */
	@DeleteMapping("/servicios/{id}")
	public ResponseEntity<?> deleteService(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		/* Captura de errores si no se puede eliminar un registro en la Base de Datos */
		try {
			
			Servicio servicio = servicioServicio.findById(id);
			
			/* Eliminamos foto anterior al actualizar la imagen */
			String nombreFotoAnterior = servicio.getSerImagen();
			
			/* Verificamos si existe una foto */
			if(nombreFotoAnterior !=null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					/* Eliminamos la foto */
					archivoFotoAnterior.delete();
				}
			}
			
			servicioServicio.delete(id);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta, con un mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("Mensaje", "Error al eliminar el servicio en la Base de Datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Enviamos la respuesta, con un mensaje de exito y un status 200 -> OK */
		response.put("mensaje", "El servicio se ha eliminado con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	/* -------------------------- METODO PARA SUBIR UNA IMAGEN -------------------------- */
	@PostMapping("/servicios/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		
		Map<String, Object> response = new HashMap<>();
		Servicio servicio = servicioServicio.findById(id);
		
		/* Verificamos si el archivo no esta vacio */
		if(!archivo.isEmpty()) {
			/* Construimos el nombre del archivo */
			String nombreArchivo = UUID.randomUUID().toString() + "_" +  archivo.getOriginalFilename().replace(" ", "");
			/* Obtenemos la ruta absoluta */
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			
			/* Copiar el archivo subido a la ruta */
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				/* Enviamos la respuesta, con un mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
				response.put("mensaje", "Error al subir la imagen de la empresa " + nombreArchivo);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			/* Eliminamos foto anterior al actualizar la imagen */
			String nombreFotoAnterior = servicio.getSerImagen();
			
			/* Verificamos si existe una foto */
			if(nombreFotoAnterior !=null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					/* Eliminamos la foto */
					archivoFotoAnterior.delete();
				}
			}
			
			/* Guardamos la foto */
			servicio.setSerImagen(nombreArchivo);
			
			/* Actualizamos */
			servicioServicio.save(servicio);
			
			response.put("Servicio", servicio);
			response.put("Mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			
		}
		
		/* Enviamos la respuesta, con un mensaje de exito y un status 200 -> OK */
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
