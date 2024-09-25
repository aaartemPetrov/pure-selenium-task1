package com.solvd.task1.page.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemsSpecificBlock {

    private final RemoteWebDriver driver;
    private WebElement storageCapacity;

    public ItemsSpecificBlock(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        new WebDriverWait(this.driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.tabbable")));
        this.storageCapacity = this.driver.findElement(
                By.cssSelector("div.ux-labels-values.col-12.ux-labels-values--storageCapacity div.ux-labels-values__values span"));
    }

    public String getStorageCapacityText() {
        return this.storageCapacity.getText();
    }

}
