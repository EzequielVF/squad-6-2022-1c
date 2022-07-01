package com.aninfo.proyectos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmpleadoService {

    public Object getEmpleado(int id){

        Object[] empleados = makeRequestEmpleados();

        if (empleados != null){
            for (Object e : empleados){
                if (e.toString().contains(String.valueOf(id))){
                    return e;
                }
            }
        }

        return new Object();
    }

    public Object[] getAllEmpleados() {
        return makeRequestEmpleados();
    }

    private Object[] makeRequestEmpleados(){
        String request = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(request, Object[].class);
    }
}