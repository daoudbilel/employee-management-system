package com.example.employee.Extension;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({ExtensionA.class, ExtensionB.class})
public class ExtensionTest {
    Logger logger = LoggerFactory.getLogger(ExtensionTest.class.getName());
    @Test
    public  void  testExtension(){
        logger.info("Extension Success");
        assertTrue(true);
    }

    @ExtendWith({ExtensionA.class})
    @Test
    public  void sum(){
        Double a =2.0;
        assertEquals(2,a);
    }



}
