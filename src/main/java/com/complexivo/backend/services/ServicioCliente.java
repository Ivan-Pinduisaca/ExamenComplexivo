package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.Cliente;
import com.complexivo.backend.repositorys.IRepoCliente;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioCliente {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */	
	@Autowired
	private IRepoCliente repoCliente;

	/*
	 * Metodos del servicio
	 * 
	 * */

	public List<Cliente> findAll() {
		return repoCliente.findAll();
	}

	public Cliente findById(Long id) {
		return repoCliente.findById(id).orElse(null);
	}

	public Cliente save(Cliente cliente) {
		return repoCliente.save(cliente);
	}

	public void deleteById(Long id) {
		repoCliente.deleteById(id);
	}

}
