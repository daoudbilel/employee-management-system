package com.example.employee.TestUnitaire;

import com.mysql.cj.log.Log;
import org.junit.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Suite
@SuiteDisplayName("test junit suite")
@SelectClasses({SimpleTestForSuite.class})
public class SuiteTest {
    Logger logger = LoggerFactory.getLogger(TestUnitaireSpring.class.getName());

    @Test
    void test2() {
        logger.info("test2 ==> success");
    }
}
