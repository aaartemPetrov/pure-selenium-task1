package com.solvd.task1;

import com.solvd.task1.page.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

public class TestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(com.solvd.task1.EbayTest.class);
    private Map<Long, WebDriver> threadIdDrivers = new HashMap<>();

    @BeforeMethod
    public synchronized void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/apetrov/Documents/SeleniumServer/chromedriver");
        this.threadIdDrivers.put(Thread.currentThread().getId(), new ChromeDriver());
    }

    @Test
    public void check1Test() throws InterruptedException {
        WebDriver driver = this.threadIdDrivers.get(Thread.currentThread().getId());
        HomePage homePage = new HomePage(driver);
        homePage.writeInSearchLine("samsung");
        Thread.sleep(2000);
    }

    @Test
    public void check2Test() throws InterruptedException {
        WebDriver driver = this.threadIdDrivers.get(Thread.currentThread().getId());
        HomePage homePage = new HomePage(driver);
        homePage.writeInSearchLine("sony");
        Thread.sleep(2000);
    }

    @Test
    public void check3Test() throws InterruptedException {
        WebDriver driver = this.threadIdDrivers.get(Thread.currentThread().getId());
        HomePage homePage = new HomePage(driver);
        homePage.writeInSearchLine("apple");
        Thread.sleep(2000);
    }

    @AfterMethod
    public synchronized void end() {
        WebDriver driver = this.threadIdDrivers.get(Thread.currentThread().getId());
        driver.close();
        driver.quit();
    }

}
