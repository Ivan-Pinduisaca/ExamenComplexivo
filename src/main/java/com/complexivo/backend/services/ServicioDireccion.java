package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.Direccion;
import com.complexivo.backend.repositorys.IRepoDireccion;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioDireccion {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */	
	@Autowired
	private IRepoDireccion repoDireccion;

	/*
	 * Metodos del servicio
	 * 
	 * */
	
	public List<Direccion> findAll(){
		return repoDireccion.findAll();
	}
	
	public Direccion findById(Long id) {
		return repoDireccion.findById(id).orElse(null);
	}
	
	public Direccion save(Direccion direccion) {
		return repoDireccion.save(direccion);
	}
	
	public void deleteById(Long id) {
		repoDireccion.deleteById(id);
	}

}
