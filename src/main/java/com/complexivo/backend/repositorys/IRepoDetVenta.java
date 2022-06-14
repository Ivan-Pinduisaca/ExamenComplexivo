package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.DetVenta;

public interface IRepoDetVenta extends JpaRepository<DetVenta, Long> {

}
