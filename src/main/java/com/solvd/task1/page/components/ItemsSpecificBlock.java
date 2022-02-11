package com.solvd.task1.page.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ItemsSpecificBlock {

    private final WebDriver driver;
    private WebElement storageCapacity;

    public ItemsSpecificBlock(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

        new WebDriverWait(this.driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.tabbable")));
        this.storageCapacity = this.driver.findElement(
                By.xpath("div.ux-labels-values.col-12.ux-labels-values--storageCapacity div.ux-labels-values__values span"));
    }

    public WebElement getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(WebElement storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

}
