package com.aninfo.proyectos.service;

import com.aninfo.proyectos.model.Recurso;
import com.aninfo.proyectos.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    public void addRecurso(Recurso recurso) {
        recursoRepository.save(recurso);
    }

    public Recurso getRecurso(int id) {
        return recursoRepository.findById(id).get();
    }

    public ArrayList<Recurso> getAllRecursos() {
        return (ArrayList<Recurso>) recursoRepository.findAll();
    }

    public void updateTarea(int id, Recurso recurso) {
        recursoRepository.save(recurso);
    }

    public void deleteRecurso(int id){
        recursoRepository.deleteById(id);
    }
}
