package com.aninfo.proyectos;

import java.util.*;
import org.junit.Assert;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import org.springframework.http.*;
import com.aninfo.proyectos.model.Tarea;
import org.junit.jupiter.api.Assertions;
import com.aninfo.proyectos.model.Proyecto;
import org.json.simple.parser.ParseException;
import com.aninfo.proyectos.repository.TareaRepository;
import com.aninfo.proyectos.repository.ProyectoRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest
@Transactional
public class TareaTest {
    private final TestRestTemplate testRestTemplate = new TestRestTemplate();
    private final Tarea tareaEsperada = new Tarea();
    private final ArrayList<Tarea> tareasEsperadas = new ArrayList<Tarea>();
    private Proyecto proyecto = new Proyecto();
    private Tarea tarea1 = new Tarea();
    private Tarea tarea2 = new Tarea();
    private ResponseEntity<Tarea> latestResponse;
    private ResponseEntity<Tarea[]> latestResponseArray;

    @Autowired
    private ProyectoRepository proyectoRepository;

    @Autowired
    private TareaRepository tareaRepository;

    @Before
    public void setup() {
        proyecto.setNombre("Un proyecto");
        proyecto.setEstado("En proceso");
        proyecto.setFechaInicio("01/05/2021");
        proyecto.setFechaFin("25/7/2022");
        proyecto.setDescripcion("descripcion del proyecto");

        tarea1.setNombre("Tarea 1");
        tarea1.setDescripcion("Descripcion de tarea 1");
        tarea1.setEstado("En proceso");
        tarea1.setFechaCreacion("fecha creacion 1");
        tarea1.setIdTicket(1);

        tarea2.setNombre("Tarea 2");
        tarea2.setDescripcion("Descripcion de tarea 2");
        tarea2.setEstado("Pendiente");
        tarea1.setFechaCreacion("fecha creacion 2");
        tarea2.setIdTicket(2);
    }

    @Given("^un proyecto")
    public void givenUnProyecto() {
        testRestTemplate.postForEntity("http://localhost:8080/proyectos", proyecto, Proyecto.class);
    }

    @When("^el empleado agrega la tarea al proyecto")
    public void whenElEmpleadoAgregaLaTareaAlProyecto() {
        int idProyecto = proyecto.getId();
        latestResponse = testRestTemplate.postForEntity("http://localhost:8080/proyectos/" + idProyecto + "/tareas", tarea1, Tarea.class);
    }

    @Then("^la tarea se agrega al proyecto")
    public void thenLaTareaSeAgregaAlProyecto() throws ParseException {
        int id = proyecto.getId();
        ArrayList<Tarea> tareasActuales = proyectoRepository.getReferenceById(id).getTareas();
        Assertions.assertEquals(proyecto.getId(), tareasActuales.get(0).getIdProyecto());
    }

    @And("^se recibe un status code de (\\d+)")
    public void andSeRecibeUnStatusCodeDe(int statusCode) {
        HttpStatus currentStatusCode = null;
        if (latestResponse != null) {
            currentStatusCode = latestResponse.getStatusCode();
        } else {
            currentStatusCode = latestResponseArray.getStatusCode();
        }
        Assertions.assertEquals(statusCode, currentStatusCode.value());
    }

    @Given("^un proyecto con tareas")
    public void givenUnProyectoConTareas() {
        tareaRepository.save(tarea1);
        tareaRepository.save(tarea2);
        proyecto.addTarea(tarea1);
        proyecto.addTarea(tarea2);
        proyectoRepository.save(proyecto);
    }

    @When("^el empleado pide las tareas del proyecto")
    public void whenElEmpleadoPideLasTareasDelProyecto() {
        int idProyecto = proyecto.getId();
        latestResponseArray = testRestTemplate.getForEntity("http://localhost:8080/proyectos/{id}/tareas", Tarea[].class, idProyecto);
        Tarea[] tareas = latestResponseArray.getBody();
        Collections.addAll(tareasEsperadas, tareas);
    }

    @Then("^se devuelven todas las tareas del proyecto")
    public void thenSeDevuelvenTodasLasTareasDelProyecto() {
        ArrayList<Tarea> tareasActuales = proyectoRepository.getReferenceById(proyecto.getId()).getTareas();
        Assertions.assertEquals(tareasEsperadas.size(), tareasActuales.size());
        Assertions.assertTrue(assertArray(tareasEsperadas, tareasActuales));
    }

    @When("^el empleado borra una tarea del proyecto")
    public void elEmpleadoBorraUnaTareaDelProyecto() {
        int idProyecto = proyecto.getId();
        int idTarea = tarea1.getId();

        latestResponse = testRestTemplate.exchange(
                "http://localhost:8080/proyectos/" + String.valueOf(idProyecto) + "/tareas/" + String.valueOf(idTarea),
                HttpMethod.DELETE,
                new HttpEntity<Tarea>(new HttpHeaders()),
                Tarea.class
        );
    }

    @Then("^la tarea se borra del proyecto")
    public void ThenLaTareaSeBorraDelProyecto() {
        Assert.assertEquals(1, (proyectoRepository.getReferenceById(proyecto.getId())).getTareas().size());
    }

    private boolean assertArray(ArrayList<Tarea> t1, ArrayList<Tarea> t2){
        boolean iguales = true;
        for (Tarea tarea : t1) {
            if (!elementoEnArray(t2, tarea)) {
                iguales = false;
                break;
            }
        }
        return iguales;
    }

    private boolean elementoEnArray(ArrayList<Tarea> tareas, Tarea tarea){
        for (Tarea t : tareas){
            if (t.getId() == tarea.getId()){
                return true;
            }
        }
        return false;
    }
}