package com.aninfo.proyectos;

import com.aninfo.proyectos.model.Tarea;
import com.aninfo.proyectos.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@SpringBootApplication
@RestController
public class ProyectosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectosApplication.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "Hola";
	}

	@Autowired
	private final TareaService tareaService = new TareaService();
	/*
	@PostMapping("/tareas")
	@ResponseStatus(HttpStatus.CREATED)
	public void addTarea(@RequestBody Tarea tarea){ tareaService.addTarea(tarea);}*/
}
