package com.solvd.task1;

import com.solvd.task1.page.HomePage;
import com.solvd.task1.page.SearchedResultPage;
import com.solvd.task1.service.WebDriverPool;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class EbayTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EbayTest.class);
    private final String localhost = "http://localhost:4444/wd/hub";

    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("chrome");
        desiredCapabilities.setPlatform(Platform.MAC);
        WebDriverPool.add(new RemoteWebDriver(new URL(localhost), desiredCapabilities));
    }

    @Test
    public void checkSearchTest() {
        WebDriver driver = WebDriverPool.get();
        HomePage homePage = new HomePage(driver);
        homePage.writeInSearchLine("samsung");
        homePage.clickSearchButton();

        SearchedResultPage searchedResultPage = new SearchedResultPage(driver);
        Assert.assertFalse(searchedResultPage.getItemsTitles().isEmpty(), "There are no searched items in list.");

        SoftAssert softAssert = new SoftAssert();
        searchedResultPage.getItemsNames().forEach(itemName -> {
            softAssert.assertTrue(itemName.toLowerCase(Locale.ROOT).contains("samsung"),
                    "Product title doesn't contain brand name \"Samsung\"");
            LOGGER.info(itemName);
        });
        softAssert.assertAll();
    }

    @AfterMethod
    public void end() {
        WebDriver driver = WebDriverPool.get();
        driver.close();
        driver.quit();
    }

}
