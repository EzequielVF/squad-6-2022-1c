package com.aninfo.proyectos.service;

import com.aninfo.proyectos.exception.NoExisteRecursoException;
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
        if (!recursoRepository.findById(recurso.getId()).isPresent()){
            recursoRepository.save(recurso);
        }
    }

    public Recurso getRecurso(int id) throws NoExisteRecursoException {
        if (recursoRepository.findById(id).isPresent()){
            return recursoRepository.findById(id).get();
        }
        throw new NoExisteRecursoException();
    }

    public ArrayList<Recurso> getAllRecursos() {
        return (ArrayList<Recurso>) recursoRepository.findAll();
    }

    public void updateRecurso(int id, Recurso recurso) {
        recursoRepository.deleteById(id);
        recursoRepository.save(recurso);
    }

    public void deleteRecurso(int id){
        recursoRepository.deleteById(id);
    }
}