package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.Modulo;
import com.complexivo.backend.repositorys.IRepoModulo;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioModulo {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */
	@Autowired
	private IRepoModulo repoModulo;

	/*
	 * Metodos del servicio
	 * 
	 * */
	public List<Modulo> findAll() {
		return repoModulo.findAll();
	}

	public Modulo findById(Long id) {
		return repoModulo.findById(id).orElse(null);
	}

	public Modulo save(Modulo modulo) {
		return repoModulo.save(modulo);
	}

	public void deleteById(Long id) {
		repoModulo.deleteById(id);
	}

}
