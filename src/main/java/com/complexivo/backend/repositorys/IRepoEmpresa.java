package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.Empresa;

public interface IRepoEmpresa extends JpaRepository<Empresa, Long> {

}
