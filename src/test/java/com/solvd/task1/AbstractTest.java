package com.solvd.task1;

import com.solvd.task1.service.DriverService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractTest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

    @BeforeMethod
    public void setup() {
        DriverService.setUp();
    }

    @AfterMethod
    public void end() {
        DriverService.shutDown();
    }

}
