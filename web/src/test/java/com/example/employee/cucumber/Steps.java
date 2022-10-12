package com.example.employee.cucumber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.socle.springboot.controller.employeeController;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Employees;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.Http2;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import repository.EmployeeRepository;
import services.EmployeeService;

import java.net.URI;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
public class Steps {

    Logger logger = LoggerFactory.getLogger(Steps.class);
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private employeeController employeeController;
    @Autowired
    private ObjectMapper objectMapper;

    private ObjectWriter objectWriter;
    private MvcResult result;

    private List<Employees> emp_list = new ArrayList<Employees>();
    private Employees employees;
    private MockMvc mockMvc;
    private Employees emp_result;

    @Autowired
    private WebApplicationContext context;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        objectWriter = objectMapper.writer();
        logger.info("start ===> true");
        emp_list = new ArrayList<Employees>();
    }

    @Given("^list des employees$")
    public void ListOfEmployee() {
        assertEquals(emp_list.size(), 0);
    }


    @When("^Utilisateur demande list employees$")
    public void getListOfEmployee() throws Exception {
        emp_list.addAll(Arrays.asList(
                objectMapper.readValue(
                        Objects.requireNonNull(testRestTemplate.getForEntity("http://localhost:8080/employees/", String.class).getBody()), Employees[].class
                )
        ));

        logger.info("size list ==> " + emp_list.size());

    }

    @Then("^List afficher$")
    public void returnListOfEmployee() {
        emp_list.stream().map(el -> {
            logger.info("id :" + el.getId() + "firstName :" + el.getFirstName());
            return el;
        }).collect(Collectors.toList());
    }

    @And("^Affichage message$")
    public void getMessage() {
        logger.info("fin test");
    }




    @Given("utilisateur avec {string} et {string} et {string}")
    public void test(String firstName, String lastName, String mail) {
        employees = new Employees(firstName, firstName, mail, "test");
        logger.info("firstName" + firstName, "firstName" + lastName, "mail", mail);
    }


    @When("^Utilisateur demander l'ajout d'un employee$")
    public void savaEmployee() {
        HttpEntity<Employees> request = new HttpEntity<>(new Employees("firstName", "lastName", "mail", "password"));
        URI location = testRestTemplate.postForLocation("http://localhost:8080/employees/add/", request);
        Assertions.assertNotNull(location);
    }


    @And("^enregistrer l'employee$")
    public void SaveEmpl() {
        logger.info("listEmployee =================>> " + employees);
        HttpEntity<Employees> request = new HttpEntity<>(employees);
        emp_result = testRestTemplate.postForObject("http://localhost:8080/employees/add", request, Employees.class);
        logger.info("empResult =======> " + emp_result);
    }


    @Then("^reponse different de null$")
    public void testResult() {
        assertNotNull(emp_result);
    }


    List<Employees> listEmp = new ArrayList<Employees>();
    MockHttpServletRequestBuilder mockRequest;

    @When("^Utilisateur modifier un employee$")
    public void updateListEmpl() {
        listEmp = listEmp.stream().map((el -> {
//            Employees employee = new Employees().builder()
//                    .id(el.getId())
//                    .firstName(el.getFirstName() + "**")
//                    .lastName(el.getLastName() + "**")
//                    .mail(el.getMail())
//                    .password("test")
//                    .build();


            Employees employee = new Employees();
            el.getId();
            el.getFirstName();
            el.getLastName();
            el.getMail();
            el.getPassword();

            try {
                String content = objectMapper.writer().writeValueAsString((employee));
                mockRequest = MockMvcRequestBuilders.put("http://localhost:8080/employee/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return el;
        })).collect(Collectors.toList());

    }

    @Then("^Test de résultat$")
    public void testResultUpdate() throws Exception {
        assertNotNull(listEmp);
    }

}
