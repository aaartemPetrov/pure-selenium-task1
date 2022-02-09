package com.solvd.task1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Locale;

public class AbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/apetrov/Documents/SeleniumServer/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void checkSearchTest() {
        HomePage homePage = new HomePage(this.driver);
        AbstractPage.sendKeys(driver, homePage.getSearchInput(), "sony");
        AbstractPage.buttonClick(driver, homePage.getSearchButton());

        SearchedResultPage searchedResultPage = new SearchedResultPage(this.driver);
        List<WebElement> searchedItems = searchedResultPage.getSearchedItems();
        Assert.assertFalse(searchedItems.isEmpty(), "There are no searched items in list.");

        SoftAssert softAssert = new SoftAssert();
        searchedItems.forEach(searchedItem -> {
            softAssert.assertTrue(searchedItem.getText().toLowerCase(Locale.ROOT).contains("sony"));
            LOGGER.info(searchedItem.getText());
        });
        softAssert.assertAll();
    }

    @AfterMethod
    public void end() {
        this.driver.close();
        this.driver.quit();
    }

}
