package com.example.employee.Extension;


import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExtensionA implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {

    Logger logger = LoggerFactory.getLogger(ExtensionA.class.getName());


    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        logger.info("Extension A =====> afterAll method");
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        logger.info("Extension A =====> afterEach method");
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        logger.info("Extension A =====> beforeAll method");
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        logger.info("Extension A =====> beforeEach method");
    }
}
