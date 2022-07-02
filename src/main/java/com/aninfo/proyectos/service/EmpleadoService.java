package com.aninfo.proyectos.service;

import com.aninfo.proyectos.model.Proyecto;
import com.aninfo.proyectos.model.Tarea;
import com.aninfo.proyectos.repository.ProyectoRepository;
import com.aninfo.proyectos.repository.TareaRepository;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Service
public class EmpleadoService {

    @Autowired
    ProyectoRepository proyectoRepository;

    @Autowired
    TareaRepository tareaRepository;

    private JSONArray makeRequestEmpleados() throws ParseException {
        String request = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos";
        RestTemplate restTemplate = new RestTemplate();
        JSONParser parser = new JSONParser();
        return (JSONArray) parser.parse(restTemplate.getForObject(request, String.class));
    }

    public JSONObject getEmpleado(long legajo) throws ParseException {
        JSONArray empleados = makeRequestEmpleados();
        for (Object empleado : empleados) {
            JSONObject empleadoJSON = (JSONObject)empleado;
            long legajo_empleado = (long)empleadoJSON.get("legajo");
            if (legajo_empleado == legajo) {
                return empleadoJSON;
            }
        }
        return new JSONObject();
    }

    public JSONArray getAllEmpleados() throws ParseException {
        return makeRequestEmpleados();
    }

    public ArrayList<Proyecto> getProyectosFromEmpleado(long legajo) {
        ArrayList<Proyecto> proyectos = (ArrayList<Proyecto>) proyectoRepository.findAll();
        ArrayList<Proyecto> proyectosDelEmpleado = new ArrayList<>();

        for (Proyecto proyecto : proyectos){
            ArrayList<Tarea> tareas = (ArrayList<Tarea>) proyecto.getTareas();
            for (Tarea tarea : tareas){
                if (tarea.getEmpleados().contains(legajo)){
                    proyectosDelEmpleado.add(proyecto);
                    break;
                }
            }
        }
        return proyectosDelEmpleado;
    }

    public ArrayList<Tarea> getTareasFromEmpleado(long legajo) {
        ArrayList<Tarea> tareas = (ArrayList<Tarea>) tareaRepository.findAll();
        ArrayList<Tarea> tareasDelEmpleado = new ArrayList<>();
        for (Tarea tarea : tareas){
            if (tarea.getEmpleados().contains(legajo)){
                tareasDelEmpleado.add(tarea);
            }
        }
        return tareasDelEmpleado;
    }
}