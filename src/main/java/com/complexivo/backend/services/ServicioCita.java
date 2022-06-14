package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.Cita;
import com.complexivo.backend.repositorys.IRepoCita;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioCita {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */	
	@Autowired
	private IRepoCita repoCita;

	/*
	 * Metodos del servicio
	 * 
	 * */

	public List<Cita> findAll() {
		return repoCita.findAll();
	}

	public Cita findById(Long id) {
		return repoCita.findById(id).orElse(null);
	}

	public Cita save(Cita cita) {
		return repoCita.save(cita);
	}

	public void deleteById(Long id) {
		repoCita.deleteById(id);
	}

}
