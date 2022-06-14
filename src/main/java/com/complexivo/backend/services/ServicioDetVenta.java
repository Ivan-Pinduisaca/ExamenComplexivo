package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.DetVenta;
import com.complexivo.backend.repositorys.IRepoDetVenta;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioDetVenta {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */	
	@Autowired
	private IRepoDetVenta repoDetVenta;

	/*
	 * Metodos del servicio
	 * 
	 * */

	public List<DetVenta> findAll() {
		return repoDetVenta.findAll();
	}

	public DetVenta findById(Long id) {
		return repoDetVenta.findById(id).orElse(null);
	}

	public DetVenta save(DetVenta detVenta) {
		return repoDetVenta.save(detVenta);
	}

	public void deleteById(Long id) {
		repoDetVenta.deleteById(id);
	}

}
