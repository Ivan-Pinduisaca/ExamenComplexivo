package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.Producto;
import com.complexivo.backend.repositorys.IRepoProducto;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioProducto {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */
	@Autowired
	private IRepoProducto repoProducto;

	/*
	 * Metodos del servicio
	 * 
	 * */
	public List<Producto> findAll() {
		return repoProducto.findAll();
	}

	public Producto findById(Long id) {
		return repoProducto.findById(id).orElse(null);
	}

	public Producto save(Producto producto) {
		return repoProducto.save(producto);
	}

	public void deleteById(Long id) {
		repoProducto.deleteById(id);
	}
	
	public Producto findByProNombre(String proNombre) {
		return repoProducto.findByProNombre(proNombre);
	}
	
}
