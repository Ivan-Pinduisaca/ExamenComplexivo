package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.Parroquia;
import com.complexivo.backend.repositorys.IRepoParroquia;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioParroquia {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */
	@Autowired
	private IRepoParroquia repoParroquia;

	/*
	 * Metodos del servicio
	 * 
	 * */
	public List<Parroquia> findAll(){
		return repoParroquia.findAll();
	}
	
	public Parroquia findById(Long id) {
		return repoParroquia.findById(id).orElse(null);
	}
	
	public Parroquia save(Parroquia parroquia) {
		return repoParroquia.save(parroquia);
	}
	
	public void deleteById(Long id) {
		repoParroquia.deleteById(id);
	}

}
