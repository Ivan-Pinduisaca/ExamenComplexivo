package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.Pais;
import com.complexivo.backend.repositorys.IRepoPais;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioPais {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */
	@Autowired
	private IRepoPais repoPais;

	/*
	 * Metodos del servicio
	 * 
	 * */
	public List<Pais> findAll(){
		return repoPais.findAll();
	}
	
	public Pais findById(Long id) {
		return repoPais.findById(id).orElse(null);
	}
	
	public Pais save(Pais pais) {
		return repoPais.save(pais);
	}
	
	public void deleteById(Long id) {
		repoPais.deleteById(id);
	}

}
