package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.Servicio;

public interface IRepoServicio extends JpaRepository<Servicio, Long> {

}
