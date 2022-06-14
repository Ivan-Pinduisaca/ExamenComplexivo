package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.complexivo.backend.models.Producto;

public interface IRepoProducto extends JpaRepository<Producto, Long> {
	
	/* 
	 * 
	 * Consulta personalizada para buscar por nombre de producto
	 * 
	 *  @Query: Consulta JPQL que hace referencia a las clases y no a las entidades
	 * 
	 * */ 
	@Query("select p from Producto p where p.proNombre = ?1")
	public Producto findByProNombre(String proNombre);

}
