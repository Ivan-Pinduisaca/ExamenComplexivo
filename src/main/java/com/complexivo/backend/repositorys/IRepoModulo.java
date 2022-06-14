package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.Modulo;

public interface IRepoModulo extends JpaRepository<Modulo, Long> {

}
