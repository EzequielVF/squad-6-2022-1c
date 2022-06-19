package com.aninfo.proyectos.model;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Proyecto {

    @Id
    private int id;
    private String name;
    private String description;

    @OneToMany(cascade = {CascadeType.REMOVE})
    private final List<Tarea> tareas = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.REMOVE})
    private final List<Recurso> recursos = new ArrayList<>();

    public Proyecto(){
    }

    public Proyecto(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void addTarea(Tarea tarea){
        tareas.add(tarea);
    }

    public void deleteTarea(int id){
        tareas.removeIf(tarea -> tarea.getId() == id);
    }

    public ArrayList<Tarea> getTareas(){
        return new ArrayList<>(tareas);
    }

    public void addRecurso(Recurso recurso){
        recursos.add(recurso);
    }

    public void deleteRecurso(int id){
        recursos.removeIf(recurso -> recurso.getId() == id);
    }

    public ArrayList<Recurso> getRecursos() {
        return new ArrayList<>(recursos);
    }
}
