package com.aninfo.proyectos.repository;

import com.aninfo.proyectos.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RecursoRepository extends JpaRepository<Recurso, Integer> {

}
