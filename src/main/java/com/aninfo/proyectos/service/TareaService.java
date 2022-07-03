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
        throw new NoExisteTareaException();
    }

    public ArrayList<Tarea> getAllTareas() {
        return (ArrayList<Tarea>) tareaRepository.findAll();
    }

    public void updateTarea(int id, Tarea tarea) {
        if (tareaRepository.findById(id).isPresent()){
            Tarea t = tareaRepository.getReferenceById(id);
            t.setNombre(tarea.getNombre());
            t.setEstado(tarea.getEstado());
            t.setDescripcion(tarea.getDescripcion());
            t.setIdTicket(tarea.getIdTicket());
            t.setIdProyecto(tarea.getIdProyecto());
            t.setFechaCreacion(tarea.getFechaCreacion());
            tareaRepository.save(t);
        }
    }

    public void deleteTarea(int id){
        tareaRepository.deleteById(id);
    }

    public ArrayList<Tarea> getAllTareasFromTicket(int id) {
        ArrayList<Tarea> tareas = (ArrayList<Tarea>) tareaRepository.findAll();
        return (ArrayList<Tarea>) tareas.stream().filter(tarea -> tarea.getIdTicket() == id).collect(Collectors.toList());
    }

    public void addEmpleadoToTarea(int id_tarea, long legajo) {
        if (tareaRepository.findById(id_tarea).isPresent()){
            Tarea tarea = tareaRepository.findById(id_tarea).get();
            tarea.addEmpleado(legajo);
            tareaRepository.save(tarea);
        }
    }

    public void deleteEmpleadoFromTarea(int id_tarea, long legajo){
        if (tareaRepository.findById(id_tarea).isPresent()){
            Tarea t = tareaRepository.getReferenceById(id_tarea);
            t.deleteEmpleado(legajo);
            tareaRepository.save(t);
        }
    }

    public void deleteAllTareas() {
        tareaRepository.deleteAll();
    }
}
