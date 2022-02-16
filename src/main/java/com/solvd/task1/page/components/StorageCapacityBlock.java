package com.solvd.task1.page.components;

import com.solvd.task1.service.WebDriverPool;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StorageCapacityBlock {

    private final WebDriver driver;
    private WebElement checkbox;
    private String labelText;

    public StorageCapacityBlock(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);

        new WebDriverWait(this.driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#x-refine__group_1__4")));
        this.checkbox = this.driver.findElement(By.cssSelector("#x-refine__group_1__4 li:nth-child(1) input"));
        String labelText = this.driver.findElement(By.cssSelector("#x-refine__group_1__4 li:nth-child(1) div:nth-child(2) span[class~=cbx]")).getText();
        labelText = StringUtils.substringBefore(labelText, "\n");
        this.labelText = StringUtils.deleteWhitespace(labelText);
    }

    public void clickCheckbox() {
        this.checkbox.click();
    }

    public String getLabelText() {
        return this.labelText;
    }

}
