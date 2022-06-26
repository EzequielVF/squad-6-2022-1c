package com.aninfo.proyectos.service;

import com.aninfo.proyectos.exception.NoExisteEmpleadoException;
import com.aninfo.proyectos.model.Empleado;
import com.aninfo.proyectos.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public void addRecurso(Empleado empleado) {
        if (!empleadoRepository.findById(empleado.getId()).isPresent()){
            empleadoRepository.save(empleado);
        }
    }

    public Empleado getRecurso(int id) throws NoExisteEmpleadoException {
        if (empleadoRepository.findById(id).isPresent()){
            return empleadoRepository.findById(id).get();
        }
        throw new NoExisteEmpleadoException();
    }

    public ArrayList<Empleado> getAllRecursos() {
        return (ArrayList<Empleado>) empleadoRepository.findAll();
    }

    public void updateRecurso(int id, Empleado empleado) {
        empleadoRepository.deleteById(id);
        empleadoRepository.save(empleado);
    }

    public void deleteRecurso(int id){
        empleadoRepository.deleteById(id);
    }
}