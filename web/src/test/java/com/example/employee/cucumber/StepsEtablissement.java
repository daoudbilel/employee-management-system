package com.example.employee.cucumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Employees;
import models.Etablissements;
import org.junit.Before;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import services.EmployeeService;
import services.EtablissementService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class StepsEtablissement {

    Logger logger = LoggerFactory.getLogger(StepsEtablissement.class);

    @Autowired
    private EtablissementService etablissementService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;

    private Etablissements etablissements;

    private MockMvc mockMvc;

    private ObjectWriter objectWriter;

    private Employees employees;


    private MvcResult result;

    List<Employees> list_emp = new ArrayList<Employees>();


    @Before()
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        objectWriter = objectMapper.writer();
        logger.info("before  ===> Success ");
    }

    @Given("^cree un etablissement$")
    public void createEtablissement() {

        etablissements = new Etablissements();
        etablissements.setNameEtablissement("test3");
    }

    @When("^Enregistrer etablissement$")
    public void saveEtablissement() throws Exception {
        String content = objectMapper.writer().writeValueAsString((etablissements));
        result = this.mockMvc.perform(post("http://localhost:8080/etablissement/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)).andReturn();
    }


    @Then("^Retourner status 200 ok$")
    public void testEtablissementsave() {
        MockHttpServletResponse response = result.getResponse();
        logger.info("Status ==> " + response.getStatus());
        assertEquals(response.getStatus(), 200);
    }


}
