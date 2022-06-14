package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.CabVenta;

public interface IRepoCabVenta extends JpaRepository<CabVenta, Long> {

}
