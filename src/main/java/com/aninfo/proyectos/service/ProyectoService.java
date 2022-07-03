package com.aninfo.proyectos.service;

import com.aninfo.proyectos.exception.NoExisteProyectoException;
import com.aninfo.proyectos.exception.NoExisteTareaException;
import com.aninfo.proyectos.model.Proyecto;
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

    public void addProyecto(Proyecto proyecto) {
        if (!proyectoRepository.findById(proyecto.getId()).isPresent()){
            if (proyecto.getTareas().size() > 0){
                for (Tarea tarea : proyecto.getTareas()){
                    tareaService.addTarea(tarea);
                }
            }
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

    public void updateProyecto(int id, Proyecto proyecto) throws NoExisteProyectoException {
        if (proyectoRepository.findById(id).isPresent()){
            proyectoRepository.save(proyecto);
        }
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

    public void deleteTareaFromProyecto(int id_proyecto, int id_tarea) throws NoExisteProyectoException {
        if (!proyectoRepository.findById(id_proyecto).isPresent()){
            throw new NoExisteProyectoException();
        }
        Proyecto proyecto = proyectoRepository.findById(id_proyecto).get();
        proyecto.deleteTarea(id_tarea);
        tareaService.deleteTarea(id_tarea);
        proyectoRepository.save(proyecto);
    }

    public void updateTareaFromProyecto(int id_proyecto, int id_tarea, Tarea tarea) throws NoExisteProyectoException {
        if (!proyectoRepository.findById(id_proyecto).isPresent()){
            throw new NoExisteProyectoException();
        }
        Proyecto proyecto = proyectoRepository.findById(id_proyecto).get();
        tareaService.addTarea(tarea);
        proyecto.deleteTarea(id_tarea);
        proyecto.addTarea(tarea);
        proyectoRepository.save(proyecto);
    }

    public ArrayList<Tarea> getAllTareasFromProyecto(int id) throws NoExisteProyectoException {
        if (!proyectoRepository.findById(id).isPresent()){
            throw new NoExisteProyectoException();
        }
        Proyecto proyecto = proyectoRepository.findById(id).get();
        return proyecto.getTareas();
    }

    public void deleteAllProyectos() {
        proyectoRepository.deleteAll();
    }

    public void setLiderOfProyecto(int id_proyecto, long legajo) {
        if (proyectoRepository.findById(id_proyecto).isPresent()){
            Proyecto proyecto = proyectoRepository.findById(id_proyecto).get();
            proyecto.setLegajoLider(legajo);
            proyectoRepository.save(proyecto);
        }
    }

    public void addTareaExistenteToProyecto(int id_proyecto, int id_tarea) throws NoExisteTareaException {
        Tarea tarea = tareaService.getTarea(id_tarea);
        Proyecto proyecto = proyectoRepository.findById(id_proyecto).get();
        proyecto.addTarea(tarea);
        proyectoRepository.save(proyecto);
    }

    public boolean empleadoEstaEnTarea(int id_proyecto, int id_tarea, long legajo) {
        if (!proyectoRepository.findById(id_proyecto).isPresent()){
            return false;
        }
        Proyecto proyecto = proyectoRepository.findById(id_proyecto).get();
        ArrayList<Tarea> tareas = proyecto.getTareas();
        if (!tareaEnProyecto(tareas, id_tarea)) {
            return false;
        }
        return empleadoEnProyecto(tareas, legajo);
    }

    private boolean tareaEnProyecto(ArrayList<Tarea> tareas, int id){
        boolean estaEnProyecto = false;
        for (Tarea tarea : tareas){
            if (tarea.getId() == id){
                estaEnProyecto = true;
                break;
            }
        }
        return estaEnProyecto;
    }

    private boolean empleadoEnProyecto(ArrayList<Tarea> tareas, long legajo){
        boolean estaEnProyecto = false;
        for (Tarea tarea : tareas){
            if (tarea.getEmpleados().contains(legajo)){
                estaEnProyecto = true;
                break;
            }
        }
        return estaEnProyecto;
    }
}

/*
json para put de proyecto
{
    "descripcion":"string",
    "estado":"string",
    "fechaFin":"string",
    "fechaInicio":"string",
    "nombre":"string",
    "legajoLider":int
}

json para put de tarea
{
  "descripcion": "string",
  "estado": "string",
  "idProyecto": int,
  "idTicket": int,
  "nombre": "string"
}
*/