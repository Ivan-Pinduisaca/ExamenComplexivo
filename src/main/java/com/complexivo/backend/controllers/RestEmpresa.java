package com.complexivo.backend.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.complexivo.backend.models.Empresa;
import com.complexivo.backend.services.ServicioEmpresa;

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
public class RestEmpresa {

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
	private ServicioEmpresa servicioEmpresa;
	
	/* Para ver como se muestra la ruta de archivo(Path) */
	private final Logger log = LoggerFactory.getLogger(RestEmpresa.class);

	/* -------------------------- METODO PARA LLISTAR TODOS LOS REGISTROS -------------------------- */
	@GetMapping("/empresas")
	public ResponseEntity<List<Empresa>> all() {
		/* Enviamos la respuesta con mensaje de exito y un status 200 -> OK */
		return ResponseEntity.ok(servicioEmpresa.findAll());
	}

	/* -------------------------- METODO PARA BUSCAR UN REGISTRO POR ID -------------------------- */
	@GetMapping("empresas/{id}")
	public ResponseEntity<?> findId(@PathVariable Long id) {

		Empresa empresa = null;
		Map<String, Object> response = new HashMap<>();

		/* Captura de errores si no se puede consultar un registro en la Base de Datos */
		try {
			empresa = servicioEmpresa.findById(id);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta con mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("Mensaje", "Error al realizar la consulta a la Base de Datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Cuando no encuentra el registro */
		if (empresa == null) {
			/* Enviamos la respuesta con un mensaje de error y un status 404 -> NOT_FOUND */
			response.put("mensaje",
					"La empresa con ID: '".concat(id.toString()).concat("' no existe en la Base de Datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		/* 
		 * Enviamos la respuesta de existencia del registro con el JSON
		 * y un status 200 -> OK
		 * 
		 *  */
		return ResponseEntity.ok(empresa);
	}

	/* -------------------------- METODO PARA INSERTAR UN REGISTRO -------------------------- */
	@PostMapping("/empresas")
	public ResponseEntity<?> create(@Valid @RequestBody Empresa empresa, BindingResult result) {

		Empresa empresaNew = null;
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
			empresaNew = servicioEmpresa.save(empresa);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta, con un mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("Mensaje", "Error al guardar la empresa en la Base de Datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Enviamos la respuesta, con un mensaje de exito y un status 201 -> CREATED */
		response.put("mensaje", "La empresa se ha creado con exito!");
		response.put("Empresa", empresaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/* -------------------------- METODO PARA ACTUALIZAR UN REGISTRO -------------------------- */
	@PutMapping("/empresas/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Empresa empresa, BindingResult result, @PathVariable Long id) {

		Empresa empresaActual = servicioEmpresa.findById(id);
		Empresa empresaUpdate = null;
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
			response.put("mensaje", errores);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		/* Verificamos si no encuentra el redistro para actualizar */
		if (empresaActual == null) {
			/* Enviamos la respuesta con un mensaje de error y un status 400 -> BAD_REQUEST */
			response.put("mensaje", "Error, no se pudo editar. La empresa con ID: "
					.concat(id.toString().concat(" no se encuentra en la Base de Datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		/* Captura de errores si no se puede actualizar un registro en la Base de Datos */
		try {
			empresaActual.setEprLogo(empresa.getEprLogo());
			empresaActual.setEprNombre(empresa.getEprNombre());
			empresaActual.setEprRuc(empresa.getEprRuc());

			empresaUpdate = servicioEmpresa.save(empresaActual);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta, con un mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("mensaje", "Error al actualizar la empresa");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Enviamos la respuesta, con un mensaje de exito y un status 201 -> CREATED */
		response.put("mensaje", "La empresa se actualizo con exito!");
		response.put("Empresa", empresaUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/* -------------------------- METODO PARA BORRAR UN REGISTRO -------------------------- */
	@DeleteMapping("/empresas/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		/* Captura de errores si no se puede eliminar un registro en la Base de Datos */
		try {
			Empresa empresa = servicioEmpresa.findById(id);
			
			/* Eliminamos foto anterior al actualizar la imagen */
			String nombreFotoAnterior = empresa.getEprLogo();
			
			/* Verificamos si existe una foto */
			if(nombreFotoAnterior !=null && nombreFotoAnterior.length() >0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					/* Eliminamos la foto */
					archivoFotoAnterior.delete();
				}
			}
			
			servicioEmpresa.deleteById(id);
		} catch (DataAccessException e) {
			/* Enviamos la respuesta, con un mensaje de error y un status 500 -> INTERNAL_SERVER_ERROR */
			response.put("mensaje", "Error al eliminar la empresa de la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		/* Enviamos la respuesta, con un mensaje de exito y un status 200 -> OK */
		response.put("mensaje", "La empresa se elimino con exito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	/* -------------------------- METODO PARA SUBIR UNA IMAGEN -------------------------- */
	@PostMapping("/empresas/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		
		Map<String, Object> response = new HashMap<>();
		Empresa empresa = servicioEmpresa.findById(id);
		
		/* Verificamos si el archivo no esta vacio */
		if(!archivo.isEmpty()) {
			/* Construimos el nombre del archivo */
			String nombreArchivo = UUID.randomUUID().toString() + "_" +  archivo.getOriginalFilename().replace(" ", "");
			/* Obtenemos la ruta absoluta */
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			log.info(rutaArchivo.toString());
			
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
			String nombreFotoAnterior = empresa.getEprLogo();
			
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
			empresa.setEprLogo(nombreArchivo);
			
			/* Actualizamos */
			servicioEmpresa.save(empresa);
			
			response.put("Empresa", empresa);
			response.put("Mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
			
		}
		
		/* Enviamos la respuesta, con un mensaje de exito y un status 200 -> OK */
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
