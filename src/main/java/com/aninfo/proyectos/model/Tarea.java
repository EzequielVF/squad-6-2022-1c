package com.aninfo.proyectos.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection
    private final List<Long> empleados = new ArrayList<>();
    /*
     * Una breve explicacion de por qué aca uso List para declarar y Arraylist como instancia:
     * Resulta que "Hibernate", una dependencia de Maven que maneja los tipos de datos para
     * las bases de datos de JPA, tiene toda una lista de tipos de datos que soporta, entre ellas
     * solo soporta tipos de dato array "that behave like arrays". Entonces vos le pones
     * "ArrayList" en la declaracion y explota todo porque a Hiberante no le gusta, pero
     * pones "List" (que es una clase abstracta, cabe aclarar) y una mantequita. Esto lo descubri
     * despues de varias horas de leer foros. :)
     * Eso quiere decir que al primer boludo que me cuestione "por que List y despues ArrayList"
     * como si fuera una mala practica o algo, voy a hacer que lo internen. Besos.
     * */

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

    public void addEmpleado(long legajo){
        empleados.add(legajo);
    }

    public void deleteEmpleado(long legajo){
        empleados.removeIf(legajo_empleado -> legajo_empleado == legajo);
    }

    public ArrayList<Long> getEmpleados(){
        return new ArrayList<>(empleados);
    }
}
