package com.aninfo.proyectos;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@CucumberContextConfiguration
@SpringBootTest
public class ProyectoTest {

    /*@Before
    public void setup() {
        System.out.println("Before any test execution");
    }*/

    @Given("^The mantequita package is empty")
    public void the_mantequita_package_is_empty(){
        int x = 2 + 2;
    }

    @When("^I want mantequita")
    public void i_want_mantequita(){
        int x = 2 + 2;
    }

    @Then("^I ask Krachitos to buy it")
    public void i_ask_krachitos_to_buy_it() {
        int x = 2 + 2;
    }
}
