package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.Empresa;
import com.complexivo.backend.repositorys.IRepoEmpresa;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioEmpresa {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */
	@Autowired
	IRepoEmpresa repoEmpresa;

	/*
	 * Metodos del servicio
	 * 
	 * */
	public List<Empresa> findAll(){
		return repoEmpresa.findAll();
	}
	
	public Empresa findById(Long id) {
		return repoEmpresa.findById(id).orElse(null);
	}
	
	public Empresa save(Empresa empresa) {
		return repoEmpresa.save(empresa);
	}
	
	public void deleteById(Long id) {
		repoEmpresa.deleteById(id);
	}

}
