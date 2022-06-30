package com.aninfo.proyectos.model;

import javax.persistence.*;

@Entity
public class Tarea {

    @Id
    private int id;
    private String description;
    private String name;
    private int id_proyecto;

    public Tarea(){

    }

    public Tarea(int id, String name, String description, int id_proyecto) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.id_proyecto = id_proyecto;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id) { this.id = id; }

    public void updateDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(String name){
        return this.name;
    }

    public void setIdProyecto(int id_proyecto){
        this.id_proyecto = id_proyecto;
    }

    public int getIdProyecto(){
        return this.id_proyecto;
    }
}
