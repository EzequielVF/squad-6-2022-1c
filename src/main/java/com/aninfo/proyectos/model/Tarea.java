package com.aninfo.proyectos.model;

import javax.persistence.*;

@Entity
public class Tarea {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;

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

    public void updateDescription(String newDescription){
        this.description = newDescription;
    }

    public String getDescription(){
        return this.description;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", description='" + description+"}";
    }
}
