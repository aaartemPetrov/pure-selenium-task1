package com.solvd.task1;

import com.solvd.task1.page.HomePage;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class FirefoxTest {

    private final Map<Long, WebDriver> threadIdDrivers = new HashMap<>();

    @BeforeMethod
    public synchronized void setup() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("firefox");
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
    public void check3Test() throws InterruptedException {
        WebDriver driver = threadIdDrivers.get(Thread.currentThread().getId());
        HomePage homePage = new HomePage(driver);
        homePage.writeInSearchLine("apple");
        Thread.sleep(2000);
    }

    @AfterMethod
    public synchronized void end() {
        WebDriver driver = threadIdDrivers.get(Thread.currentThread().getId());
    }

    @AfterSuite
    public synchronized void quit() {
        for(Map.Entry<Long, WebDriver> entry : threadIdDrivers.entrySet()) {
            synchronized (entry.getValue()) {
                if (entry.getValue() != null) {
                    entry.getValue().quit();
                }
            }
        }
    }

}
