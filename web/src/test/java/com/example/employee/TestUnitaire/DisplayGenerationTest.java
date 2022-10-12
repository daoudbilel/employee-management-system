package com.example.employee.TestUnitaire;

import com.example.employee.CamelCaseToStatementGenerator;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayNameGeneration(CamelCaseToStatementGenerator.class)
public class DisplayGenerationTest {

    Logger logger = LoggerFactory.getLogger(DisplayGenerationTest.class.getName());

    @Test
    public void getTestOne() {
        Double a = 2.0;
        Double b = 2.0;
        Assumptions.assumeTrue(a.equals(b));
    }

    @Test
    public void getTestTow() {
        Assumptions.assumeFalse("test".equals("test"));
    }

    @Test
    public void getTestThree() {
        assumingThat("test".equals("test"),
                () -> {
                    logger.info("test");
                    assertEquals(2, 2);
                });
    }

}
