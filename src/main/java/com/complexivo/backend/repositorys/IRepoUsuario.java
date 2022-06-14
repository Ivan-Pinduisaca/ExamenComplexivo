package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.Usuario;

public interface IRepoUsuario extends JpaRepository<Usuario, Long> {

}
