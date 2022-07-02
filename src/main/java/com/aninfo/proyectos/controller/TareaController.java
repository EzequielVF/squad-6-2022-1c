package com.aninfo.proyectos.controller;

import com.aninfo.proyectos.exception.NoExisteTareaException;
import com.aninfo.proyectos.model.Tarea;
import com.aninfo.proyectos.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
public class TareaController {

    @Autowired
    private final TareaService tareaService = new TareaService();

    /*
     * GET REQUESTS /////////////////////////////////////////////////////////////////////////////////
     * */

    @RequestMapping(method=RequestMethod.GET, value="/tareas")
    public ArrayList<Tarea> getAllTareas(){
        return tareaService.getAllTareas();
    }

    @RequestMapping(method=RequestMethod.GET, value="/tareas/{id}")
    public Tarea getTarea(@PathVariable("id") int id) throws NoExisteTareaException {
        return tareaService.getTarea(id);
    }

    @RequestMapping(method=RequestMethod.GET, value="/tickets/{id}/tareas")
    public ArrayList<Tarea> getTareasFromTicket(@PathVariable("id") int id){
        return tareaService.getAllTareasFromTicket(id);
    }

    /*
     * POST REQUESTS /////////////////////////////////////////////////////////////////////////////////
     * */

    @RequestMapping(method=RequestMethod.POST, value="/tareas")
    public void addTarea(@RequestBody Tarea tarea){ tareaService.addTarea(tarea); }

    /*
     * DELETE REQUESTS /////////////////////////////////////////////////////////////////////////////////
     * */

    @RequestMapping(method=RequestMethod.DELETE, value="/tareas/{id}")
    public void deleteTarea(@PathVariable("id") int id){
        tareaService.deleteTarea(id);
    }

    /*
     * PUT REQUESTS /////////////////////////////////////////////////////////////////////////////////
     * */

    @RequestMapping(method=RequestMethod.PUT, value="/tareas/{id}")
    public void updateTarea(@PathVariable("id") int id, @RequestBody Tarea tarea){
        tareaService.updateTarea(id, tarea);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/tareas/{id1}/empleado/{id2}")
    public void addEmpleadoToTarea(@PathVariable("id1")int id_tarea, @PathVariable("id2") long legajo){
        tareaService.addEmpleadoToTarea(id_tarea, legajo);
    }
}
