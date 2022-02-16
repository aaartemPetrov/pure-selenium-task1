package com.solvd.task1;

import com.solvd.task1.page.HomePage;
import com.solvd.task1.service.WebDriverPool;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

public class ChromeTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(ChromeTest.class);
    private final String localhost = "http://localhost:4444/wd/hub";

    @BeforeMethod
    public synchronized void setup() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("chrome");
        desiredCapabilities.setPlatform(Platform.MAC);
        WebDriverPool.add(new RemoteWebDriver(new URL(localhost), desiredCapabilities));
    }

    @Test
    public void check1Test() throws InterruptedException {
        WebDriver driver = WebDriverPool.get();
        HomePage homePage = new HomePage(driver);
        homePage.writeInSearchLine("samsung");
        Thread.sleep(2000);
        Assert.assertFalse(true);
    }

    @Test
    public void check2Test() throws InterruptedException {
        WebDriver driver = WebDriverPool.get();
        HomePage homePage = new HomePage(driver);
        homePage.writeInSearchLine("sony");
        Thread.sleep(2000);
        Assert.assertFalse(true);
    }

    @Test
    public void check3Test() throws InterruptedException {
        WebDriver driver = WebDriverPool.get();
        HomePage homePage = new HomePage(driver);
        homePage.writeInSearchLine("apple");
        Thread.sleep(2000);
        Assert.assertFalse(true);
    }

    @AfterMethod
    public synchronized void end(ITestResult testResult) throws IOException {
        WebDriver driver = WebDriverPool.get();
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotName = "[" + testResult.getName() + "] " + getScreenshotName();
            FileUtils.copyFile(screenshotFile, new File("/Users/apetrov/Documents/" + screenshotName + ".png"));
            LOGGER.info("Screenshot is captured successfully.");
        }
        driver.close();
        driver.quit();
    }

    public String getScreenshotName() {
        String className = StringUtils.substringAfterLast(getClass().getName(), ".");
        String dateTime = StringUtils.substringBefore(LocalDateTime.now().toString(), ".");
        String result = "[" + className + "] [" + dateTime + "]";
        return result;
    }

}
