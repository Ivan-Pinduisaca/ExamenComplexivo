package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.TipoPago;

public interface IRepoTipoPago extends JpaRepository<TipoPago, Long> {

}
