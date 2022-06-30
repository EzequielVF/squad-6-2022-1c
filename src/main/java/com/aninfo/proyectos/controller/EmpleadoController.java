package com.aninfo.proyectos.controller;
import com.aninfo.proyectos.exception.NoExisteEmpleadoException;
import com.aninfo.proyectos.model.Empleado;
import org.springframework.web.bind.annotation.*;
import com.aninfo.proyectos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@RestController
public class EmpleadoController {

    @Autowired
    private final EmpleadoService empleadoService = new EmpleadoService();

    @RequestMapping(method= RequestMethod.GET, value="/empleados")
    public ArrayList<Empleado> getAllEmpleados(){
        return empleadoService.getAllEmpleados();
    }

    @RequestMapping(method=RequestMethod.GET, value="/empleados/{id}")
    public Empleado getEmpleado(@PathVariable("id") int id) throws NoExisteEmpleadoException {
        return empleadoService.getEmpleado(id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/recursos")
    public void addEmpleado(@RequestBody Empleado empleado){ empleadoService.addEmpleado(empleado); }

    @RequestMapping(method=RequestMethod.DELETE, value="/recursos/{id}")
    public void deleteEmpleado(@PathVariable("id") int id){
        empleadoService.deleteEmpleado(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/empleados/{id}")
    public void updateEmpleado(@PathVariable("id") int id, @RequestBody Empleado empleado){
        empleadoService.updateEmpleado(id, empleado);
    }
}
