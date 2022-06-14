package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.Canton;
import com.complexivo.backend.repositorys.IRepoCanton;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioCanton {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */	
	@Autowired
	private IRepoCanton repoCanton;

	/*
	 * Metodos del servicio
	 * 
	 * */
	
	public List<Canton> findAll(){
		return repoCanton.findAll();
	}
	
	public Canton findById(Long id) {
		return repoCanton.findById(id).orElse(null);
	}
	
	public Canton save(Canton canton) {
		return repoCanton.save(canton);
	}
	
	public void deleteById(Long id) {
		repoCanton.deleteById(id);
	}

}
