package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.Persona;

public interface IRepoPersona extends JpaRepository<Persona, Long> {

}
