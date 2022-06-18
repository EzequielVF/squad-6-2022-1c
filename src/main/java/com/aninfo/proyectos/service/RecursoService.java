package com.aninfo.proyectos.service;

import com.aninfo.proyectos.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;
}
