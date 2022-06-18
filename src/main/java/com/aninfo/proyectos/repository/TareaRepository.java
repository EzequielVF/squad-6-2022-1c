package com.aninfo.proyectos.repository;

import com.aninfo.proyectos.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {

}