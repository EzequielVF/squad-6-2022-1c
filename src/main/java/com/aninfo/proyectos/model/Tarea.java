package com.aninfo.proyectos.model;

import javax.persistence.*;

@Entity
public class Tarea {

    @Id
    private int id;
    private String description;
    private int id_proyecto;

    public Tarea(){

    }

    public Tarea(int id, String description) {
        this.id = id;
        this.description = description;
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

    public void setIdProyecto(int id_proyecto){
        this.id_proyecto = id_proyecto;
    }

    public int getIdProyecto(){
        return this.id_proyecto;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", description='" + description+"}";
    }
}
