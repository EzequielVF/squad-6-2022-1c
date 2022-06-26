package com.aninfo.proyectos.controller;
import com.aninfo.proyectos.exception.NoExisteEmpleadoException;
import com.aninfo.proyectos.model.Empleado;
import org.springframework.web.bind.annotation.*;
import com.aninfo.proyectos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@RestController
public class RecursoController {

    @Autowired
    private final EmpleadoService empleadoService = new EmpleadoService();

    @RequestMapping(method= RequestMethod.GET, value="/recursos")
    public ArrayList<Empleado> getAllRecursos(){
        return empleadoService.getAllRecursos();
    }

    @RequestMapping(method=RequestMethod.GET, value="/recursos/{id}")
    public Empleado getRecurso(@PathVariable("id") int id) throws NoExisteEmpleadoException {
        return empleadoService.getRecurso(id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/recursos")
    public void addRecurso(@RequestBody Empleado empleado){ empleadoService.addRecurso(empleado); }

    @RequestMapping(method=RequestMethod.DELETE, value="/recursos/{id}")
    public void deleteRecurso(@PathVariable("id") int id){
        empleadoService.deleteRecurso(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/recursos/{id}")
    public void updateRecurso(@PathVariable("id") int id, @RequestBody Empleado empleado){
        empleadoService.updateRecurso(id, empleado);
    }
}
