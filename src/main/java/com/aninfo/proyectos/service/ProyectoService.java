package com.aninfo.proyectos.service;

import com.aninfo.proyectos.model.Proyecto;
import com.aninfo.proyectos.model.Recurso;
import com.aninfo.proyectos.model.Tarea;
import com.aninfo.proyectos.repository.ProyectoRepository;
import com.aninfo.proyectos.repository.RecursoRepository;
import com.aninfo.proyectos.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private RecursoRepository recursoRepository;

    public void addProyecto(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    public Proyecto getProyecto(int id) {
        return proyectoRepository.findById(id).get();
    }

    public ArrayList<Proyecto> getAllProyectos() {
        return (ArrayList<Proyecto>) proyectoRepository.findAll();
    }

    public void updateProyecto(int id, Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }

    public void deleteProyecto(int id){
        proyectoRepository.deleteById(id);
    }

    public void addTareaToProyecto(int id, Tarea tarea){
        Proyecto proyecto = proyectoRepository.findById(id).get();
        proyecto.addTarea(tarea);
        tarea.setIdProyecto(id);
        tareaRepository.save(tarea);
        proyectoRepository.save(proyecto);
    }

    public void addRecursoToProyecto(int id, Recurso recurso) {
        Proyecto proyecto = proyectoRepository.findById(id).get();
        proyecto.addRecurso(recurso);
        recurso.setIdProyecto(id);
        recursoRepository.save(recurso);
        proyectoRepository.save(proyecto);
    }
}
