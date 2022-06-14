package com.complexivo.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complexivo.backend.models.Empleado;
import com.complexivo.backend.repositorys.IRepoEmpleado;

/*
 * 
 * @Service: Indica que es un Clase Servicio, para conectarse a repositorios 
 * 
 * */
@Service
public class ServicioEmpleado {

	/*
	 * @Autowired: Indica la inyeccion de dependencias
	 * 
	 */
	@Autowired
	private IRepoEmpleado repoEmpleado;

	/*
	 * Metodos del servicio
	 * 
	 * */
	public List<Empleado> findAll() {
		return repoEmpleado.findAll();
	}

	public Empleado findById(Long id) {
		return repoEmpleado.findById(id).orElse(null);
	}

	public Empleado save(Empleado empleado) {
		return repoEmpleado.save(empleado);
	}

	public void deleteById(Long id) {
		repoEmpleado.deleteById(id);
	}

}
