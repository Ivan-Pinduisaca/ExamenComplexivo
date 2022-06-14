package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.RolUsuario;

public interface IRepoRolUsuario extends JpaRepository<RolUsuario, Long> {

}
