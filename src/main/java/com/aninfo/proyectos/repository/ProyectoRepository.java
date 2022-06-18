package com.aninfo.proyectos.repository;

import com.aninfo.proyectos.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

}
