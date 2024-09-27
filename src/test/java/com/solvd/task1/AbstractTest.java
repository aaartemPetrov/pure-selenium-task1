package com.solvd.task1;

import com.solvd.task1.service.DriverService;
import com.zebrunner.agent.core.registrar.CurrentTestRun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public abstract class AbstractTest {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

    @BeforeSuite
    public void preSetup() {
        CurrentTestRun.setBuild("v.1.2.3.4.5");
        CurrentTestRun.setLocale("enGB");
        CurrentTestRun.setPlatform("WEB");
    }

    @BeforeMethod
    public void setup() {
        DriverService.setUp();
    }

    @AfterMethod
    public void end() {
        DriverService.shutDown();
    }

}
