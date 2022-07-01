package com.aninfo.proyectos.controller;
import com.aninfo.proyectos.model.Proyecto;
import org.springframework.web.bind.annotation.*;
import com.aninfo.proyectos.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@RestController
public class EmpleadoController {

    @Autowired
    private final EmpleadoService empleadoService = new EmpleadoService();

    @RequestMapping(method= RequestMethod.GET, value="/empleados")
    public Object[] getAllEmpleados(){
        return empleadoService.getAllEmpleados();
    }

    @RequestMapping(method=RequestMethod.GET, value="/empleados/{id}")
    public Object getEmpleado(@PathVariable("id") int id){
        return empleadoService.getEmpleado(id);
    }
}
