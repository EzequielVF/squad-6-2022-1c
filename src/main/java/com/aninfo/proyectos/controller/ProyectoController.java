package com.aninfo.proyectos.controller;

import com.aninfo.proyectos.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProyectoController {

    @Autowired
    private final ProyectoService proyectoService = new ProyectoService();

}
