package com.aninfo.proyectos.repository;

import com.aninfo.proyectos.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

}
