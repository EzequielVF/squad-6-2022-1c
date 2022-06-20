package com.aninfo.proyectos.service;

import com.aninfo.proyectos.exception.NoExisteProyectoException;
import com.aninfo.proyectos.model.Proyecto;
import com.aninfo.proyectos.model.Recurso;
import com.aninfo.proyectos.model.Tarea;
import com.aninfo.proyectos.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private final TareaService tareaService = new TareaService();

    @Autowired
    private final RecursoService recursoService = new RecursoService();

    public void addProyecto(Proyecto proyecto) {
        if (!proyectoRepository.findById(proyecto.getId()).isPresent()){
            proyectoRepository.save(proyecto);
        }
    }

    public Proyecto getProyecto(int id) throws NoExisteProyectoException {
        if (proyectoRepository.findById(id).isPresent()){
            return proyectoRepository.findById(id).get();
        }
        throw new NoExisteProyectoException();
    }

    public ArrayList<Proyecto> getAllProyectos() {
        return (ArrayList<Proyecto>) proyectoRepository.findAll();
    }

    public void updateProyecto(int id, Proyecto proyecto) {
        proyectoRepository.deleteById(id);
        proyectoRepository.save(proyecto);
    }

    public void deleteProyecto(int id){
        proyectoRepository.deleteById(id);
    }

    public void addTareaToProyecto(int id, Tarea tarea) throws NoExisteProyectoException {
        if (!proyectoRepository.findById(id).isPresent()){
            throw new NoExisteProyectoException();
        }
        Proyecto proyecto = proyectoRepository.findById(id).get();
        proyecto.addTarea(tarea);
        tarea.setIdProyecto(id);
        tareaService.addTarea(tarea);
        proyectoRepository.save(proyecto);
    }

    public void addRecursoToProyecto(int id, Recurso recurso) throws NoExisteProyectoException {
        if (!proyectoRepository.findById(id).isPresent()){
            throw new NoExisteProyectoException();
        }
        Proyecto proyecto = proyectoRepository.findById(id).get();
        proyecto.addRecurso(recurso);
        recurso.setIdProyecto(id);
        recursoService.addRecurso(recurso);
        proyectoRepository.save(proyecto);
    }

    public void deleteTareaFromProyecto(int id_proyecto, int id_tarea) throws NoExisteProyectoException {
        if (!proyectoRepository.findById(id_proyecto).isPresent()){
            throw new NoExisteProyectoException();
        }
        Proyecto proyecto = proyectoRepository.findById(id_proyecto).get();
        proyecto.deleteTarea(id_tarea);
        tareaService.deleteTarea(id_tarea);
        proyectoRepository.save(proyecto);
    }

    public void deleteTareaRecursoProyecto(int id_proyecto, int id_recurso) throws NoExisteProyectoException {
        if (!proyectoRepository.findById(id_proyecto).isPresent()){
            throw new NoExisteProyectoException();
        }
        Proyecto proyecto = proyectoRepository.findById(id_proyecto).get();
        proyecto.deleteRecurso(id_recurso);
        recursoService.deleteRecurso(id_recurso);
        proyectoRepository.save(proyecto);
    }

    public void updateTareaFromProyecto(int id_proyecto, int id_tarea, Tarea tarea) throws NoExisteProyectoException {
        if (!proyectoRepository.findById(id_proyecto).isPresent()){
            throw new NoExisteProyectoException();
        }
        Proyecto proyecto = proyectoRepository.findById(id_proyecto).get();
        tareaService.updateTarea(id_tarea, tarea);
        proyecto.deleteTarea(id_tarea);
        proyecto.addTarea(tarea);
        proyectoRepository.save(proyecto);
    }

    public void updateRecursoFromProyecto(int id_proyecto, int id_recurso, Recurso recurso) throws NoExisteProyectoException {
        if (!proyectoRepository.findById(id_proyecto).isPresent()){
            throw new NoExisteProyectoException();
        }
        Proyecto proyecto = proyectoRepository.findById(id_proyecto).get();
        recursoService.updateRecurso(id_recurso, recurso);
        proyecto.deleteRecurso(id_recurso);
        proyecto.addRecurso(recurso);
        proyectoRepository.save(proyecto);
    }

    public ArrayList<Tarea> getAllTareasFromProyecto(int id) throws NoExisteProyectoException {
        if (!proyectoRepository.findById(id).isPresent()){
            throw new NoExisteProyectoException();
        }
        Proyecto proyecto = proyectoRepository.findById(id).get();
        return proyecto.getTareas();
    }

    public ArrayList<Recurso> getAllRecursosFromProyecto(int id) throws NoExisteProyectoException {
        if (!proyectoRepository.findById(id).isPresent()){
            throw new NoExisteProyectoException();
        }
        Proyecto proyecto = proyectoRepository.findById(id).get();
        return proyecto.getRecursos();
    }
}
