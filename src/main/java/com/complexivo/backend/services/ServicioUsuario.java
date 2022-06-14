package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.Usuario;
import com.complexivo.backend.repositorys.IRepoUsuario;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioUsuario {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */
	@Autowired
	private IRepoUsuario repoUsuario;

	/*
	 * Metodos del servicio
	 * 
	 * */
	public List<Usuario> findAll(){
		return repoUsuario.findAll();
	}
	
	public Usuario findById(Long id) {
		return repoUsuario.findById(id).orElse(null);
	}
	
	public Usuario save(Usuario usuario) {
		return repoUsuario.save(usuario);
	}
	
	public void deleteById(Long id) { 
		repoUsuario.deleteById(id);
	}

}
