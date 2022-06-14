package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.Cita;

public interface IRepoCita extends JpaRepository<Cita, Long> {

}
