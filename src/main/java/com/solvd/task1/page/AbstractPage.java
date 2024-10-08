package com.solvd.task1.page;

import com.solvd.task1.service.WebDriverPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {

    private static final Logger LOGGER = LogManager.getLogger(AbstractPage.class);
    private static final Duration TIMEOUT = Duration.ofSeconds(10);
    private RemoteWebDriver driver;
    protected String pageURL;

    public AbstractPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void open() {
        this.driver.get(this.pageURL);
    }

    protected void click(WebElement element) {
        new WebDriverWait(WebDriverPool.get(), TIMEOUT).until(ExpectedConditions.elementToBeClickable(element)).click();
        LOGGER.info("\"" + element.getTagName() + "\"" + " was clicked.");
    }

    protected void sendKeys(WebElement element, String string) {
        new WebDriverWait(WebDriverPool.get(), TIMEOUT).until(ExpectedConditions.visibilityOf(element)).sendKeys(string);
        LOGGER.info("\"" + string + "\"" + " was wrote in to a " + "\"" + element.getTagName() + "\".");
    }

    public RemoteWebDriver getDriver() {
        return this.driver;
    }

    public void setDriver(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public String getPageURL() {
        return this.pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }
}
