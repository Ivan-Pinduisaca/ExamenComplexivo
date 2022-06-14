package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.Persona;
import com.complexivo.backend.repositorys.IRepoPersona;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioPersona {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */
	@Autowired
	private IRepoPersona repoPersona;

	/*
	 * Metodos del servicio
	 * 
	 * */
	public List<Persona> findAll() {
		return repoPersona.findAll();
	}

	public Persona findById(Long id) {
		return repoPersona.findById(id).orElse(null);
	}

	public Persona save(Persona persona) {
		return repoPersona.save(persona);
	}

	public void deleteById(Long id) {
		repoPersona.deleteById(id);
	}
}
