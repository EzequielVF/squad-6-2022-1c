package com.aninfo.proyectos.service;

import com.aninfo.proyectos.model.Tarea;
import com.aninfo.proyectos.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public void addTarea(Tarea tarea) {
        tareaRepository.save(tarea);
    }

    public Tarea getTarea(int id) {
        return tareaRepository.findById(id).get();
    }

    public ArrayList<Tarea> getAllTareas() {
        return (ArrayList<Tarea>) tareaRepository.findAll();
    }

    public void updateTarea(int id, Tarea tarea) {
        tareaRepository.save(tarea);
    }

    public void deleteTarea(int id){
        tareaRepository.deleteById(id);
    }
}
