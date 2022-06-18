package com.aninfo.proyectos.controller;
import com.aninfo.proyectos.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecursoController {

    @Autowired
    private final RecursoService recursoService = new RecursoService();

}
