package com.example.employee.Extension;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExtensionB implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {

    Logger logger = LoggerFactory.getLogger(ExtensionB.class.getName());

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        logger.info("Extension B =====> afterAll method");
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        logger.info("Extension B =====> afterEach method");
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        logger.info("Extension B =====> beforeAll method");
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        logger.info("Extension B =====> beforeEach method");
    }
}
