package com.solvd.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractPage {

    private static final Logger LOGGER = LogManager.getLogger(AbstractPage.class);
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    public static void buttonClick(WebDriver driver, WebElement element) {
        LOGGER.info("\"" + element.getAccessibleName() + "\"" + " button was clicked.");
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void sendKeys(WebDriver driver, WebElement element, String string) {
        LOGGER.info("\"" + string + "\"" + " was wrote in to a search line");
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOf(element)).sendKeys(string);
    }


}
