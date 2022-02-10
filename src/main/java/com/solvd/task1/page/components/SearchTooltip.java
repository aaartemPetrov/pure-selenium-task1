package com.solvd.task1.page.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchTooltip {

    private WebDriver driver;
    private WebElement searchTooltip;
    private List<WebElement> tooltips;

    public SearchTooltip(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        /*this.driver.get(driver.getCurrentUrl());*/
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        WebElement searchTooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gAC")));
        if(searchTooltip != null) {
            this.searchTooltip = searchTooltip;
        }
        this.tooltips = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#gAC li a.ghAC_sugg")));
    }

    public WebElement getSearchTooltip() {
        return this.searchTooltip;
    }

    public void setSearchTooltip(WebElement searchTooltip) {
        this.searchTooltip = searchTooltip;
    }

    public List<WebElement> getTooltips() {
        return this.tooltips;
    }

    public void setTooltips(List<WebElement> tooltips) {
        this.tooltips = tooltips;
    }

}
