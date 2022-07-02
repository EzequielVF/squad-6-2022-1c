package com.aninfo.proyectos.controller;

import com.aninfo.proyectos.model.Proyecto;
import com.aninfo.proyectos.model.Tarea;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;
import com.aninfo.proyectos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;

@RestController
public class EmpleadoController {

    @Autowired
    private final EmpleadoService empleadoService = new EmpleadoService();

    /*
     * GET REQUESTS /////////////////////////////////////////////////////////////////////////////////
     * */

    @RequestMapping(method= RequestMethod.GET, value="/empleados")
    public JSONArray getAllEmpleados() throws ParseException {
        return empleadoService.getAllEmpleados();
    }

    @RequestMapping(method=RequestMethod.GET, value="/empleados/{id}")
    public JSONObject getEmpleado(@PathVariable("id") long id) throws ParseException {
        return empleadoService.getEmpleado(id);
    }

    @RequestMapping(method=RequestMethod.GET, value="/empleados/{id}/proyectos")
    public ArrayList<Proyecto> getProyectosFromEmpleado(@PathVariable("id") long id){
        return empleadoService.getProyectosFromEmpleado(id);
    }

    @RequestMapping(method=RequestMethod.GET, value="/empleados/{id}/tareas")
    public ArrayList<Tarea> getTareasFromEmpleado(@PathVariable("id") long legajo){
        return empleadoService.getTareasFromEmpleado(legajo);
    }
}
