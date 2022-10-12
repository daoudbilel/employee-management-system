package com.example.employee.TestUnitaire;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderTest {

    @Test
    @Order(2)
    void order2() {
    }

    @Test
    @Order(1)
    void order1() {
    }

    @Test
    @Order(3)
    void order3() {
    }
}
