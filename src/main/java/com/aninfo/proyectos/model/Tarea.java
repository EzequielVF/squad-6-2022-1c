package com.aninfo.proyectos.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nombre;
    private String descripcion;
    private String estado;
    private int idTicket;
    private int idProyecto;
    //@ManyToOne
    //@JoinColumn(name="idProyecto")
    //private Proyecto proyecto;

    public Tarea(){

    }

    public Tarea(int id, String nombre, String descripcion, String estado, int idProyecto, int idTicket) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idTicket = idTicket;
        this.idProyecto = idProyecto;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id) { this.id = id; }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setEstado(String estado) { this.estado = estado; }

    public String getEstado() { return this.estado; }

    public void setIdProyecto(int id){
        this.idProyecto = id;
    }

    public int getIdProyecto(){
        return this.idProyecto;
    }

    public void setIdTicket(int idTicket){
        this.idTicket = idTicket;
    }

    public int getIdTicket() {
        return this.idTicket;
    }
}
