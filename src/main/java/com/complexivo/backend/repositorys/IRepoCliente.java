package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.Cliente;

public interface IRepoCliente extends JpaRepository<Cliente, Long> {

}
