package com.aninfo.proyectos.service;

import com.aninfo.proyectos.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

}
