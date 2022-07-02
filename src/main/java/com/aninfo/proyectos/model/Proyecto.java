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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nombre;
    private String estado;
    private String fechaInicio;
    private String fechaFin;
    private long legajoLider;

    @OneToMany(cascade = {CascadeType.REMOVE})
    private final List<Tarea> tareas = new ArrayList<>();

    public Proyecto(){
    }

    public Proyecto(int id, String nombre, String estado, String fechaInicio, String fechaFin, long legajoLider){
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.legajoLider = legajoLider;
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

    public String getFechaInicio() { return this.fechaInicio; }

    public String getFechaFin() { return this.fechaFin; }

    public void setId(int id){
        this.id = id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }

    public void addTarea(Tarea tarea){
        tareas.add(tarea);
    }

    public void deleteTarea(int id){
        tareas.removeIf(tarea -> tarea.getId() == id);
    }

    public ArrayList<Tarea> getTareas(){
        return new ArrayList<>(tareas);
    }

    public void setLegajoLider(long legajo){
        this.legajoLider = legajo;
    }

    public long getLegajoLider(){
        return this.legajoLider;
    }
}