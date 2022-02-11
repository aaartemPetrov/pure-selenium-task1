package com.solvd.task1.page.components;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FromToPriceLink {

    private final WebDriver driver;
    private WebElement link;
    private final int fromPrice;
    private final int toPrice;

    public FromToPriceLink(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        WebElement fromToPriceLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#s0-14-11-6-3-query_answer1-answer-2-1-0-list li:nth-child(2)")));
        if (fromToPriceLink != null) {
            this.link = fromToPriceLink;
        }
        String priceString = this.link.getText();
        priceString = StringUtils.replaceChars(priceString, ",", ".");
        String[] fromToPrices = StringUtils.substringsBetween(priceString, "$", ".");
        this.fromPrice = Integer.parseInt(fromToPrices[0]);
        this.toPrice = Integer.parseInt(fromToPrices[1]);
        System.out.println();
    }

    public WebElement getLink() {
        return link;
    }

    public void setLink(WebElement link) {
        this.link = link;
    }

    public int getFromPrice() {
        return this.fromPrice;
    }

    public int getToPrice() {
        return this.toPrice;
    }

}
