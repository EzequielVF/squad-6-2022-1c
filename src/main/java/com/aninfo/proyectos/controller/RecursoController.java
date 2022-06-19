package com.aninfo.proyectos.controller;
import com.aninfo.proyectos.model.Recurso;
import com.aninfo.proyectos.model.Tarea;
import com.aninfo.proyectos.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class RecursoController {

    @Autowired
    private final RecursoService recursoService = new RecursoService();

    @RequestMapping(method= RequestMethod.GET, value="/recursos")
    public ArrayList<Recurso> getAllRecursos(){
        return recursoService.getAllRecursos();
    }

    @RequestMapping(method=RequestMethod.GET, value="/recursos/{id}")
    public Recurso getRecurso(@PathVariable("id") int id){
        return recursoService.getRecurso(id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/recursos")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRecurso(@RequestBody Recurso recurso){ recursoService.addRecurso(recurso); }

    @RequestMapping(method=RequestMethod.DELETE, value="/recursos/{id}")
    public void deleteRecurso(@PathVariable("id") int id){
        recursoService.deleteRecurso(id);
    }

}
