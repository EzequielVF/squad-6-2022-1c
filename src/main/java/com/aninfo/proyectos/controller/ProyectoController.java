package com.aninfo.proyectos.controller;

import com.aninfo.proyectos.model.Proyecto;
import com.aninfo.proyectos.model.Recurso;
import com.aninfo.proyectos.model.Tarea;
import com.aninfo.proyectos.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
public class ProyectoController {

    @Autowired
    private final ProyectoService proyectoService = new ProyectoService();

    @RequestMapping(method= RequestMethod.GET, value="/proyectos")
    public ArrayList<Proyecto> getAllProyectos(){
        return proyectoService.getAllProyectos();
    }

    @RequestMapping(method=RequestMethod.GET, value="/proyectos/{id}")
    public Proyecto getProyecto(@PathVariable("id") int id){
        return proyectoService.getProyecto(id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/proyectos")
    public void addProyecto(@RequestBody Proyecto proyecto){ proyectoService.addProyecto(proyecto); }

    @RequestMapping(method=RequestMethod.DELETE, value="/proyectos/{id}")
    public void deleteProyecto(@PathVariable("id") int id){
        proyectoService.deleteProyecto(id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/proyectos/{id}/tareas")
    public void addTareaToProyecto(@PathVariable("id") int id, @RequestBody Tarea tarea){
        proyectoService.addTareaToProyecto(id, tarea);
    }

    @RequestMapping(method=RequestMethod.POST, value="/proyectos/{id}/recursos")
    public void addRecursoToProyecto(@PathVariable("id") int id, @RequestBody Recurso recurso){
        proyectoService.addRecursoToProyecto(id, recurso);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/proyectos/{id1}/tareas/{id2}")
    public void deleteTareaFromProyecto(@PathVariable("id1") int id_proyecto, @PathVariable("id2") int id_tarea){
        proyectoService.deleteTareaFromProyecto(id_proyecto, id_tarea);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/proyectos/{id1}/recursos/{id2}")
    public void deleteRecursoFromProyecto(@PathVariable("id1") int id_proyecto, @PathVariable("id2") int id_recurso) {
        proyectoService.deleteTareaRecursoProyecto(id_proyecto, id_recurso);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/proyectos/{id}")
    public void updateProyecto(@PathVariable("id")int id, @RequestBody Proyecto proyecto){
        proyectoService.updateProyecto(id, proyecto);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/proyectos/{id1}/tareas/{id2}")
    public void updateTareaFromProyecto(@PathVariable("id1") int id_proyecto, @PathVariable("id2") int id_tarea, @RequestBody Tarea tarea){
        proyectoService.updateTareaFromProyecto(id_proyecto, id_tarea, tarea);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/proyectos/{id1}/recursos/{id2}")
    public void updateRecursoFromProyecto(@PathVariable("id1") int id_proyecto, @PathVariable("id2") int id_recurso, @RequestBody Recurso recurso){
        proyectoService.updateRecursoFromProyecto(id_proyecto, id_recurso, recurso);
    }

    @RequestMapping(method=RequestMethod.GET, value="/proyectos/{id}/tareas")
    public ArrayList<Tarea> getAllTareasFromProyecto(@PathVariable("id") int id){
        return proyectoService.getAllTareasFromProyecto(id);
    }

    @RequestMapping(method=RequestMethod.GET, value="/proyectos/{id}/recursos")
    public ArrayList<Recurso> getAllRecursosFromProyecto(@PathVariable("id") int id){
        return proyectoService.getAllRecursosFromProyecto(id);
    }
}