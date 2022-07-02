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
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;

@CucumberContextConfiguration
@SpringBootTest
public class ProyectoTest {

    private final TestRestTemplate testRestTemplate = new TestRestTemplate();
    private final Proyecto proyectoEsperado = new Proyecto();
    private final ArrayList <Proyecto> proyectosEsperados = new ArrayList<Proyecto>();

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
        HttpStatus currentStatusCode = latestResponse.getStatusCode();
        Assertions.assertEquals(currentStatusCode.value(), statusCode);
    }

    @And("^el proyecto se agrega")
    public void andElProyectoSeAgrega() {
        Proyecto proyectoActual = proyectoRepository.getReferenceById(proyectoEsperado.getId());
        Assertions.assertEquals(proyectoEsperado.getId(), proyectoActual.getId());
    }
/*
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

    private boolean assertArray(ArrayList<Proyecto> p1, ArrayList<Proyecto> p2){
        boolean iguales = true;
        for (Proyecto proyecto : p1) {
            if (!p2.contains(proyecto)) {
                iguales = false;
                break;
            }
        }
        return iguales;
    }
 */
}