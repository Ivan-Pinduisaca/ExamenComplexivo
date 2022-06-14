package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.Pais;

public interface IRepoPais extends JpaRepository<Pais, Long> {

}
