package com.aninfo.proyectos.controller;

import com.aninfo.proyectos.model.Proyecto;
import com.aninfo.proyectos.model.Recurso;
import com.aninfo.proyectos.model.Tarea;
import com.aninfo.proyectos.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public void addProyecto(@RequestBody Proyecto proyecto){ proyectoService.addProyecto(proyecto); }

    @RequestMapping(method=RequestMethod.POST, value="/proyectos/{id}/tareas")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTareaToProyecto(@PathVariable("id") int id, @RequestBody Tarea tarea){
        proyectoService.addTareaToProyecto(id, tarea);
    }

    @RequestMapping(method=RequestMethod.POST, value="/proyectos/{id}/recursos")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRecursoToProyecto(@PathVariable("id") int id, @RequestBody Recurso recurso){
        proyectoService.addRecursoToProyecto(id, recurso);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/proyectos/{id}")
    public void deleteProyecto(@PathVariable("id") int id){
        proyectoService.deleteProyecto(id);
    }
}
