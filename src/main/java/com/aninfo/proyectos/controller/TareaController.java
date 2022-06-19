package com.aninfo.proyectos.controller;

import com.aninfo.proyectos.model.Tarea;
import com.aninfo.proyectos.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TareaController {

    @Autowired
    private final TareaService tareaService = new TareaService();

    @RequestMapping(method=RequestMethod.GET, value="/tareas")
    public ArrayList<Tarea> getAllTareas(){
        return tareaService.getAllTareas();
    }

    @RequestMapping(method=RequestMethod.GET, value="/tareas/{id}")
    public Tarea getTarea(@PathVariable("id") int id){
        return tareaService.getTarea(id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/tareas")
    public void addTarea(@RequestBody Tarea tarea){ tareaService.addTarea(tarea); }

    @RequestMapping(method=RequestMethod.DELETE, value="/tareas/{id}")
    public void deleteTarea(@PathVariable("id") int id){
        tareaService.deleteTarea(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/tareas/{id}")
    public void updateTarea(@PathVariable("id") int id, @RequestBody Tarea tarea){
        tareaService.updateTarea(id, tarea);
    }
}
