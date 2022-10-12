package com.example.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.socle.springboot.controller.employeeController;
import com.socle.springboot.dto.EmployeeDto;
import com.socle.springboot.employee.EmployeeApplication;
import io.cucumber.java.an.E;
import jdk.jfr.ContentType;
import models.Employees;
import models.Etablissements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import services.EmployeeService;
import services.EtablissementService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EmployeeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeTestIntegration {

    Logger logger = LoggerFactory.getLogger(EmployeeApplicationTest.class);

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private employeeController employeeController;
    @Autowired
    @Spy
    @InjectMocks
    private EmployeeService employeeService;
    @Autowired
    private EtablissementService etablissementService;

    @Mock
    EmployeeDto employeeDto;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        logger.info("Start ===========> True");
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("http://localhost:8080/employees/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("taktek")));
    }

    @Test
    public void getAllEmployeesTest() throws Exception {
        List<Employees> list = new ArrayList<Employees>();
        Employees emp1 = new Employees(1L, "daoud", "bilel", "daoudbilel@gmail.com", "test");
        Employees emp2 = new Employees(2L, "taktak", "fedi", "feditaktak@gmail.com", "test1");
        Employees emp3 = new Employees(3L, "makwar", "oussema", "oussemamakwar@gmail.com", "test3");

        list.add(emp1);
        list.add(emp2);
        list.add(emp3);


        employeeController mock = org.mockito.Mockito.mock(employeeController.class);
        when(mock.index()).thenReturn((ResponseEntity<List<EmployeeDto>>) list);

        List<EmployeeDto> list_emp = (List<EmployeeDto>) employeeController.index();

        logger.info("List employees ====> " + list_emp.size());

        list_emp.stream().map(el -> {
            logger.info("List employees ====> " + el.getId());
            list.stream().map(elem -> {
                assertNotEquals(el.getId(), elem.getId());
                return elem;
            }).collect(Collectors.toList());
            return el;
        }).collect(Collectors.toList());

    }

    @Test
    public void findByIDTest() throws Exception {
        Employees emp1 = new Employees(2L, "taktek", "fedi", "fedi@gmail.com", "test");


        when(employeeService.findOneById(emp1.getId())).thenReturn(emp1);
        this.mockMvc.perform(get("http://localhost:8080/employees/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.firstName", is("taktek")));

    }

    @Test
    public void addTest() throws Exception {
        Etablissements etablissements = new Etablissements(1L, "Sifast");
        Etablissements etab = etablissementService.addEtablissement(etablissements);
//        Employees employees = Employees.builder()
//                .firstName("daoud")
//                .lastName("bilel")
//                .mail("daoubilel@gmail.com")
//                .password("test")
//                .etablissements(etab)
//                .build();

        Employees employees = new Employees();
        employees.setFirstName("daoud");
        employees.setLastName("bilel");
        employees.setMail("daoubilel@gmail.com");
        employees.setPassword("test");
        employees.setEtablissements(etab);


        String content = objectMapper.writeValueAsString((employees));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("http://localhost:8080/employees/add")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(content);


        mockMvc.perform(mockRequest)
//                .andExpect(jsonPath("$", notNullValue()))
//                .andExpect(jsonPath("$.firstName", is("daoud")))
//                .andExpect(jsonPath("$.lastName", is("bilel")))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


    }


    @Test
    public void deleteTest() throws Exception {
        long employeeId = 6L;
        willDoNothing().given(employeeService).deleteEmployee(employeeId);
        ResultActions responce = mockMvc.perform(delete("http://localhost:8080/employees/delete/" + employeeId));
        responce.andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void updateTest() throws Exception {
        Etablissements etablissements = new Etablissements(5l, "Sifast");
        Etablissements etab = etablissementService.addEtablissement(etablissements);
//        Employees employees = Employees.builder()
//                .id(2L)
//                .firstName("daoud")
//                .lastName("bilel")
//                .mail("daoubilel@gmail.com")
//                .password("test")
//                .etablissements(etab)
//                .build();


        Employees employees = new Employees();
        employees.setId(2L);
        employees.setFirstName("daoud");
        employees.setLastName("bilel");
        employees.setMail("daoubilel@gmail.com");
        employees.setPassword("test");
        employees.setEtablissements(etab);

        String content = objectWriter.writeValueAsString(employees);

        MockHttpServletRequestBuilder mockrequest = MockMvcRequestBuilders.post("http://localhost:8080/employees/update/")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(content);

        mockMvc.perform(mockrequest)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", notNullValue()))
//                .andExpect(jsonPath("$.id", is((2L))))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


    }

    @Test
    @DisplayName("test display name ")
    void testTag() {
        int x = 7;
        int y = 2;
        int delta = 5;
        assertEquals(x, y, delta);
        logger.info("test display name  ======> TRUE");
    }


    @AfterEach
    void after() throws Exception {
        mockMvc.perform(get("http://localhost:8080/employees/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("taktek")));
        logger.info("AfterEach - excutes after each test method in this class success");

    }


}
