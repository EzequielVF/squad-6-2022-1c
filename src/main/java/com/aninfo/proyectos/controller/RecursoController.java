package com.aninfo.proyectos.controller;
import com.aninfo.proyectos.exception.NoExisteRecursoException;
import com.aninfo.proyectos.model.Recurso;
import org.springframework.web.bind.annotation.*;
import com.aninfo.proyectos.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;

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
    public Recurso getRecurso(@PathVariable("id") int id) throws NoExisteRecursoException {
        return recursoService.getRecurso(id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/recursos")
    public void addRecurso(@RequestBody Recurso recurso){ recursoService.addRecurso(recurso); }

    @RequestMapping(method=RequestMethod.DELETE, value="/recursos/{id}")
    public void deleteRecurso(@PathVariable("id") int id){
        recursoService.deleteRecurso(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/recursos/{id}")
    public void updateRecurso(@PathVariable("id") int id, @RequestBody Recurso recurso){
        recursoService.updateRecurso(id, recurso);
    }
}
