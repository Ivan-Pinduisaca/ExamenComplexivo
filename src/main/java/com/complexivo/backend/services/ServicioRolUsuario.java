package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.RolUsuario;
import com.complexivo.backend.repositorys.IRepoRolUsuario;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioRolUsuario {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */
	@Autowired
	private IRepoRolUsuario repoRolUsuario;

	/*
	 * Metodos del servicio
	 * 
	 * */
	public List<RolUsuario> findAll() {
		return repoRolUsuario.findAll();
	}
	
	public RolUsuario findById(Long id) {
		return repoRolUsuario.findById(id).orElse(null);
	}
	
	public RolUsuario save(RolUsuario rolUsuario) {
		return repoRolUsuario.save(rolUsuario);
	}
	
	public void deleteById(Long id) {
		repoRolUsuario.deleteById(id);
	}

}
