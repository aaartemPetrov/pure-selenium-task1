package com.solvd.task1;

import com.solvd.task1.page.HomePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ChromeTest {

    private final Map<Long, WebDriver> threadIdDrivers = new HashMap<>();

    @BeforeMethod
    public synchronized void setup() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("chrome");
        desiredCapabilities.setPlatform(Platform.MAC);
        this.threadIdDrivers.put(Thread.currentThread().getId(),
                new RemoteWebDriver(new URL("http://192.168.89.11:4444/wd/hub"), desiredCapabilities));
    }

    @Test
    public void check1Test() throws InterruptedException {
        WebDriver driver = threadIdDrivers.get(Thread.currentThread().getId());
        HomePage homePage = new HomePage(driver);
        homePage.writeInSearchLine("samsung");
        Thread.sleep(2000);
    }

    @Test
    public void check2Test() throws InterruptedException {
        WebDriver driver = threadIdDrivers.get(Thread.currentThread().getId());
        HomePage homePage = new HomePage(driver);
        homePage.writeInSearchLine("sony");
        Thread.sleep(2000);
    }

    @Test
    public void check3Test() throws InterruptedException, IOException {
        WebDriver driver = threadIdDrivers.get(Thread.currentThread().getId());
        try {
            HomePage homePage = new HomePage(driver);
            homePage.writeInSearchLine("apple");
            Thread.sleep(2000);
            Assert.assertFalse(true);
        } catch (Throwable e) {
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("/Users/apetrov/Documents/1.png"));
        }
    }

    @AfterMethod
    public synchronized void end() {
        WebDriver driver = threadIdDrivers.get(Thread.currentThread().getId());
        driver.close();
        driver.quit();
    }

}
