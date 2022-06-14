package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.Rol;
import com.complexivo.backend.repositorys.IRepoRol;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioRol {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */
	@Autowired
	private IRepoRol repoRol;

	/*
	 * Metodos del servicio
	 * 
	 * */
	public List<Rol> findAll(){
		return repoRol.findAll();
	}
	
	public Rol findById(Long id) {
		return repoRol.findById(id).orElse(null);
	}
	
	public Rol save(Rol rol) {
		return repoRol.save(rol);
	}
	
	public void deleteById(Long id) {
		repoRol.deleteById(id);
	}

}
