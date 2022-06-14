package com.complexivo.backend.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.complexivo.backend.models.Empleado;

public interface IRepoEmpleado extends JpaRepository<Empleado, Long> {

}
