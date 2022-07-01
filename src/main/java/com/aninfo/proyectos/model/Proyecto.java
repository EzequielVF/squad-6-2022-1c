package com.aninfo.proyectos.model;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nombre;
    private String estado;
    private Date fechaInicio;
    private Date fechaFin;

    // formato fecha en JSON para POST request:
    // "fecha":"YYYY-MM-ddTHH:mm:ss"

    @OneToMany(cascade = {CascadeType.REMOVE}/*, mappedBy = "proyecto"*/)
    private final List<Tarea> tareas = new ArrayList<>();



    public Proyecto(){
    }

    public Proyecto(int id, String nombre, String estado, Date fechaInicio, Date fechaFin){
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getId() {
        return this.id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getEstado(){
        return this.estado;
    }

    public Date getFechaInicio() { return this.fechaInicio; }

    public Date getFechaFin() { return this.fechaFin; }

    public void setId(int id){
        this.id = id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }

    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }

    public void addTarea(Tarea tarea){
        tareas.add(tarea);
    }

    public void deleteTarea(int id){
        tareas.removeIf(tarea -> tarea.getId() == id);
    }

    public ArrayList<Tarea> getTareas(){
        return new ArrayList<>(tareas);
    }
}
