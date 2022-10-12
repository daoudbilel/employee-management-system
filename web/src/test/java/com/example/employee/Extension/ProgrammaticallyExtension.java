package com.example.employee.Extension;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProgrammaticallyExtension {

    Logger logger = LoggerFactory.getLogger(ProgrammaticallyExtension.class.getName());

    @Order(1)
    @RegisterExtension
    static ExtensionA extensionA = new ExtensionA();

    @Order(2)
    @RegisterExtension
    static ExtensionB extensionB = new ExtensionB();

    @Test
    public void testExtension() {
        logger.info("Extension Success");
        assertTrue(true);
    }


}
