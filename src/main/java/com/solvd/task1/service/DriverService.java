package com.solvd.task1.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class DriverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverService.class);
    private static final String localhost = Configuration.getProperty("selenium_url");

    public static void setUp() {
        String seleniumURL = localhost;
        if("".equals(seleniumURL)) seleniumURL = System.getenv("selenium_url");

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName(Configuration.getProperty("browser"));
        try {
            WebDriverPool.add(new RemoteWebDriver(new URL(seleniumURL), desiredCapabilities));
        } catch (MalformedURLException e) {
            LOGGER.error("MalformedURLException in EbayTest.setup(). Error message: " + e.getMessage() + " url: " + seleniumURL );
        }
    }

    public static void shutDown() {
        RemoteWebDriver driver = WebDriverPool.get();
        driver.quit();
    }

}
