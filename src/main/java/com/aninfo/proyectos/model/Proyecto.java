package com.aninfo.proyectos.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Proyecto {

    @Id
    private int id;
    private String name;
    private String description;

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
}
