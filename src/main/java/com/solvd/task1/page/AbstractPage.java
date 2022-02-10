package com.solvd.task1.page;

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

    public static void click(WebDriver driver, WebElement element) {
        String elementName = element.getAccessibleName();
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(element)).click();
        LOGGER.info("\"" + elementName + "\"" + " was clicked.");
    }

    public static void sendKeys(WebDriver driver, WebElement element, String string) {
        String elementName = element.getAccessibleName();
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOf(element)).sendKeys(string);
        LOGGER.info("\"" + elementName + "\"" + " was wrote in to a " + "\"" + element.getAccessibleName() + "\".");
    }


}
