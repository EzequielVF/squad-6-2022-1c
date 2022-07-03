package com.aninfo.proyectos.service;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.aninfo.proyectos.model.Tarea;
import org.json.simple.parser.JSONParser;
import com.aninfo.proyectos.model.Proyecto;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.aninfo.proyectos.repository.TareaRepository;
import com.aninfo.proyectos.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmpleadoService {

    @Autowired
    ProyectoRepository proyectoRepository;

    @Autowired
    TareaRepository tareaRepository;

    RestTemplate restTemplate = new RestTemplate();
    JSONParser parser = new JSONParser();

    private JSONArray requestAuxiliarEmpleados() throws ParseException {
        String request = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos";
        return (JSONArray) parser.parse(restTemplate.getForObject(request, String.class));
    }

    private JSONArray makeRequestEmpleados() throws ParseException {
        String request = "https://squad5-recursos.herokuapp.com/api/empleados";
        return (JSONArray) parser.parse(restTemplate.getForObject(request, String.class));
    }

    private JSONObject makeRequestEmpleado(long legajo) throws ParseException {
        String request = "https://squad5-recursos.herokuapp.com/api/empleados/"+String.valueOf(legajo);
        return (JSONObject) parser.parse(restTemplate.getForObject(request, String.class));
    }

    public JSONObject getEmpleado(long legajo) throws ParseException {
        JSONArray empleadosJson = requestAuxiliarEmpleados();
        JSONObject empleado = new JSONObject();
        for (Object empleadoJson : empleadosJson){
            long legajoEmpleado = (long)((JSONObject)empleadoJson).get("legajo");
            if (legajoEmpleado == legajo){
                empleado = (JSONObject)empleadoJson;
                break;
            }
        }
        return empleado;
        //return makeRequestEmpleado(legajo);
    }

    public JSONArray getAllEmpleados() throws ParseException {
        return requestAuxiliarEmpleados();
        //return makeRequestEmpleados();
    }

    public ArrayList<Proyecto> getProyectosFromEmpleado(long legajo) {
        ArrayList<Proyecto> proyectos = (ArrayList<Proyecto>) proyectoRepository.findAll();
        ArrayList<Proyecto> proyectosDelEmpleado = new ArrayList<>();

        for (Proyecto proyecto : proyectos){
            ArrayList<Tarea> tareas = proyecto.getTareas();
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