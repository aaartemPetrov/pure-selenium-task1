package com.solvd.task1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Locale;

public class EbayTest {

    private static final Logger LOGGER = LogManager.getLogger(EbayTest.class);
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver","/Users/apetrov/Documents/SeleniumServer/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void checkTest() {
        HomePage homePage = new HomePage(this.driver);
        homePage.writeInSearchLine("Samsung");
        homePage.clickSearchButton();

        SearchedResultPage searchedResultPage = new SearchedResultPage(this.driver);
        List<WebElement> searchedItems = searchedResultPage.getSearchedItems();
        Assert.assertFalse(searchedItems.isEmpty(), "There are no searched items in list.");

        SoftAssert softAssert = new SoftAssert();
        searchedItems.forEach(searchedItem -> {
            softAssert.assertTrue(searchedItem.getText().toLowerCase(Locale.ROOT).contains("samsung"));
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
