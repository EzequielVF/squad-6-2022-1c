package com.aninfo.proyectos.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Recurso {

    @Id
    private int id;
    private String name;
    private String description;
    private int id_proyecto;

    public Recurso(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Recurso() {

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

    public void setIdProyecto(int id_proyecto){
        this.id_proyecto = id_proyecto;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public int getIdProyecto(){
        return this.id_proyecto;
    }
}