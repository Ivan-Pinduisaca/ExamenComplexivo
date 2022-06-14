package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.Provincia;

public interface IRepoProvincia extends JpaRepository<Provincia, Long> {

}
