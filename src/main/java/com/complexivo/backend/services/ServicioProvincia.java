package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.Provincia;
import com.complexivo.backend.repositorys.IRepoProvincia;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioProvincia {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */
	@Autowired
	private IRepoProvincia repoProvincia;

	/*
	 * Metodos del servicio
	 * 
	 * */
	public List<Provincia> findAll(){
		return repoProvincia.findAll();
	}
	
	public Provincia findById(Long id) {
		return repoProvincia.findById(id).orElse(null);
	}
	
	public Provincia save(Provincia provincia) {
		return repoProvincia.save(provincia);
	}
	
	public void deleteById(Long id) {
		repoProvincia.deleteById(id);
	}

}
