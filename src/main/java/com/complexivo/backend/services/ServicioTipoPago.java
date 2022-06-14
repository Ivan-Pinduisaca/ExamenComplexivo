package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.TipoPago;
import com.complexivo.backend.repositorys.IRepoTipoPago;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioTipoPago {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */
	@Autowired
	private IRepoTipoPago repoTipoPago;

	/*
	 * Metodos del servicio
	 * 
	 * */
	public List<TipoPago> findAll() {
		return repoTipoPago.findAll();
	}

	public TipoPago findById(Long id) {
		return repoTipoPago.findById(id).orElse(null);
	}

	public TipoPago save(TipoPago tipoPago) {
		return repoTipoPago.save(tipoPago);
	}

	public void deleteById(Long id) {
		repoTipoPago.deleteById(id);
	}

}
