package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.Servicio;
import com.complexivo.backend.repositorys.IRepoServicio;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioServicio {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */
	@Autowired
	private IRepoServicio repoServicio;

	/*
	 * Metodos del servicio
	 * 
	 * */
	public List<Servicio> findAll(){
		return repoServicio.findAll();
	}
	
	public Servicio findById(Long id) {
		return repoServicio.findById(id).orElse(null);
	}
	
	public Servicio save(Servicio servicio) {
		return repoServicio.save(servicio);
	}
	
	public void delete(Long id) {
		repoServicio.deleteById(id);
	}

}
