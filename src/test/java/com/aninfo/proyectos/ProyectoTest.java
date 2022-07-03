package com.aninfo.proyectos;

import com.aninfo.proyectos.model.Proyecto;
import com.aninfo.proyectos.repository.ProyectoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.dao.EmptyResultDataAccessException;

@CucumberContextConfiguration
@SpringBootTest
public class ProyectoTest {

    private final TestRestTemplate testRestTemplate = new TestRestTemplate();
    private final Proyecto proyectoEsperado = new Proyecto();
    private final ArrayList <Proyecto> proyectosEsperados = new ArrayList<Proyecto>();
    private Proyecto proyectoDb = new Proyecto();
    private ResponseEntity<Proyecto> latestResponse;
    private ResponseEntity<Proyecto[]> latestResponseArray;

    private ObjectMapper objectMapper;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Before
    public void setup() {
        proyectoEsperado.setNombre("Primer proyecto");
        proyectoEsperado.setEstado("En proceso");
        proyectoEsperado.setFechaInicio("01/05/2021");
        proyectoEsperado.setFechaFin("25/7/2022");
        proyectoEsperado.setDescripcion("descripcion del proyecto");

        proyectoDb.setNombre("Segundo proyecto");
        proyectoDb.setEstado("En proceso");
        proyectoDb.setFechaInicio("01/07/2021");
        proyectoDb.setFechaFin("01/7/2022");
        proyectoDb.setDescripcion("descripcion del proyecto 2");
        proyectoRepository.save(proyectoDb);
    }

    @Given("^soy un empleado")
    public void givenSoyUnEmpleado(){
    }

    @When("^el empleado agrega un proyecto")
    public void whenElEmpleadoAgregaUnProyecto(){
        latestResponse = testRestTemplate.postForEntity("http://localhost:8080/proyectos", proyectoEsperado, Proyecto.class);
    }

    @Then("^el empleado recibe un status code de (\\d+)")
    public void thenElEmpleadoRecibeUnStatusCodeDe(int statusCode) {

        HttpStatus currentStatusCode = null;
        if (latestResponse != null) {
            currentStatusCode = latestResponse.getStatusCode();
        } else {
            currentStatusCode = latestResponseArray.getStatusCode();
        }
        Assertions.assertEquals(statusCode, currentStatusCode.value());
    }

    @And("^el proyecto se agrega")
    public void andElProyectoSeAgrega() {
        Proyecto proyectoActual = proyectoRepository.getReferenceById(proyectoEsperado.getId());
        Assertions.assertEquals(proyectoEsperado.getId(), proyectoActual.getId());
    }

    @When("^el empleado pide todos los proyectos")
    public void whenElEmpleadoPideTodosLosProyectos() {
        latestResponseArray = testRestTemplate.getForEntity("http://localhost:8080/proyectos", Proyecto[].class);
        Proyecto[] ps = latestResponseArray.getBody();
        Collections.addAll(proyectosEsperados, ps);
    }

    @And("^se devuelven todos los proyectos")
    public void andSeDevuelvenTodosLosProyectos() {
        ArrayList<Proyecto> proyectosActuales = (ArrayList<Proyecto>) proyectoRepository.findAll();
        Assertions.assertTrue(assertArray(proyectosActuales, proyectosEsperados));
    }

    @When("^el empleado borra un proyecto sin tareas")
    public void whenElEmpleadoBorraUnProyectoSinTareas() {
        int id = proyectoDb.getId();
        latestResponse  = testRestTemplate.exchange("http://localhost:8080/proyectos/{id}", HttpMethod.DELETE, new HttpEntity<Proyecto>(new HttpHeaders()), Proyecto.class, id);
    }

    @And("^el proyecto se borra")
    public void andElProyectoSeBorra() {
        Assert.assertFalse((proyectoRepository.findById(proyectoDb.getId()).isPresent()));
    }

    /*@When("^el empleado cambia el nombre de un proyecto a (.*)")
    public void whenElEmpleadoCambiaElNombreDeUnProyectoA(String nombre) {
        int id = proyectoDb.getId();

        Map<String, String> map = new HashMap<>();
        map.put("nombre", proyectoDb.getNombre());
        map.put("estado", proyectoDb.getEstado());
        map.put("fechaInicio", proyectoDb.getFechaInicio());
        map.put("fechFin", proyectoDb.getFechaFin());

        JSONObject p = new JSONObject(map);

        latestResponse = testRestTemplate.exchange();
    }*/

    @When("^el empleado cambia el nombre de un proyecto a (.*)")
    public void whenElEmpleadoCambiaElNombreDeUnProyectoA(String nombre){
        int id = proyectoDb.getId();
        proyectoDb.setNombre(nombre);
        latestResponse  = testRestTemplate.exchange("http://localhost:8080/proyectos/{id}", HttpMethod.PUT, new HttpEntity<Proyecto>(new HttpHeaders()), Proyecto.class, id);
    }

    @And("^el nombre del proyecto cambia")
    public void andElNombreDelProyectoCambia() {
        Proyecto proyectoActual = latestResponse.getBody();
        Assert.assertEquals(proyectoActual.getNombre(), proyectoDb.getNombre());
    }

    @When("^el empleado borra un proyecto que no existe")
    public void whenElEmpleadoBorraUnProyectoQueNoExiste() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, ()->proyectoRepository.deleteById(0));
    }

    private boolean assertArray(ArrayList<Proyecto> p1, ArrayList<Proyecto> p2){
        boolean iguales = true;
        for (Proyecto proyecto : p1) {
            if (!elementoEnArray(p2, proyecto)) {
                iguales = false;
                break;
            }
        }
        return iguales;
    }

    private boolean elementoEnArray(ArrayList<Proyecto> p, Proyecto proyecto){
        for (Proyecto proy : p){
            if (proy.getId() == proyecto.getId()){
                return true;
            }
        }
        return false;
    }
}