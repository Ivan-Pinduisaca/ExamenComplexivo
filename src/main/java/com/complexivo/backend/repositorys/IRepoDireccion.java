package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.Direccion;

public interface IRepoDireccion extends JpaRepository<Direccion, Long> {

}
