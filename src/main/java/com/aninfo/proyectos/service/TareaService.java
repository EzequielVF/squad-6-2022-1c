package com.aninfo.proyectos.service;

import com.aninfo.proyectos.exception.NoExisteTareaException;
import com.aninfo.proyectos.model.Tarea;
import com.aninfo.proyectos.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public void addTarea(Tarea tarea) {
        if (!tareaRepository.findById(tarea.getId()).isPresent()){
            tareaRepository.save(tarea);
        }
    }

    public Tarea getTarea(int id) throws NoExisteTareaException {
        if (tareaRepository.findById(id).isPresent()){
            return tareaRepository.findById(id).get();
        }
        //throw new NoExisteTareaException();
        return null;
    }

    public ArrayList<Tarea> getAllTareas() {
        return (ArrayList<Tarea>) tareaRepository.findAll();
    }

    public void updateTarea(int id, Tarea tarea) {
        tareaRepository.deleteById(id);
        tareaRepository.save(tarea);
    }

    public void deleteTarea(int id){
        tareaRepository.deleteById(id);
    }

    public ArrayList<Tarea> getAllTareasFromTicket(int id) {
        ArrayList<Tarea> tareas = (ArrayList<Tarea>) tareaRepository.findAll();
        return (ArrayList<Tarea>) tareas.stream().filter(tarea -> tarea.getIdTicket() == id).collect(Collectors.toList());
    }
}
