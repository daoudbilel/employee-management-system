package com.example.employee.TestUnitaire;

import com.socle.springboot.testUnitaire.TestJunitService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleTestForSuite {

    Logger logger = LoggerFactory.getLogger(TestUnitaireSpring.class.getName());

    @Autowired
    private TestJunitService testJunitService;

    @Test
    void test1() {
        logger.info("test1 ==> success");
    }

    @Test
    @Tag("tagtest")
    public void tagTest() {
        assertNull(testJunitService.getObject(null));
        logger.info("test1  tag 1 ==> success");
    }
}

