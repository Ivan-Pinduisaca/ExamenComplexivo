package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.Sucursal;

public interface IRepoSucursal extends JpaRepository<Sucursal, Long> {

}
