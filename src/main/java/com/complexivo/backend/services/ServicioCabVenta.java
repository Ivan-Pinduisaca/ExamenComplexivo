package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.CabVenta;
import com.complexivo.backend.repositorys.IRepoCabVenta;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioCabVenta {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */
	@Autowired
	private IRepoCabVenta repoCabVenta;

	/*
	 * Metodos del servicio
	 * 
	 * */
	
	public List<CabVenta> findAll() {
		return repoCabVenta.findAll();
	}

	public CabVenta findById(Long id) {
		return repoCabVenta.findById(id).orElse(null);
	}

	public CabVenta save(CabVenta cabVenta) {
		return repoCabVenta.save(cabVenta);
	}

	public void deleteById(Long id) {
		repoCabVenta.deleteById(id);
	}
}
