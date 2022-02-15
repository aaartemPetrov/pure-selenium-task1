package com.solvd.task1;

import com.solvd.task1.page.HomePage;
import com.solvd.task1.service.WebDriverPool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class TestTest {

    @BeforeMethod
    public synchronized void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/apetrov/Documents/SeleniumServer/chromedriver");
        WebDriverPool.add(new ChromeDriver());
    }

    @Test
    public void check1Test() throws InterruptedException {
        WebDriver driver = WebDriverPool.get();
        HomePage homePage = new HomePage(driver);
        homePage.writeInSearchLine("samsung");
        Thread.sleep(2000);
    }

    @Test
    public void check2Test() throws InterruptedException {
        WebDriver driver = WebDriverPool.get();
        HomePage homePage = new HomePage(driver);
        homePage.writeInSearchLine("sony");
        Thread.sleep(2000);
    }

    @Test
    public void check3Test() throws InterruptedException {
        WebDriver driver = WebDriverPool.get();
        HomePage homePage = new HomePage(driver);
        homePage.writeInSearchLine("apple");
        Thread.sleep(2000);
    }

    @AfterMethod
    public synchronized void end() {
        WebDriver driver = WebDriverPool.get();
        driver.close();
        driver.quit();
    }

}
