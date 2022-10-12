package com.example.employee.cucumber;


import com.socle.springboot.employee.EmployeeApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.spring.CucumberContextConfiguration;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

@CucumberContextConfiguration
@SpringBootTest(classes = {EmployeeApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, tags = "", features = "web/src/main/resources")
@PropertySource("classpath : src/main/resources/application.properties")
public class CucumberIntegrationTest {
}
