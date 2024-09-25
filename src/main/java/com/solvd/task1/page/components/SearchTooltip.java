package com.solvd.task1.page.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchTooltip {

    private final RemoteWebDriver driver;
    private WebElement searchTooltip;
    private List<WebElement> tooltips;

    public SearchTooltip(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        WebElement searchTooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gAC")));
        if (searchTooltip != null) {
            this.searchTooltip = searchTooltip;
        }
        this.tooltips = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#gAC li a.ghAC_sugg")));
    }

    public int tooltipsCount() {
        return tooltips.size();
    }

}
