package com.solvd.task1.page.components;

import com.solvd.task1.page.AbstractPage;
import com.solvd.task1.service.WebDriverPool;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UnderPriceLink extends AbstractPage {

    private WebElement link;
    private final int price;

    public UnderPriceLink(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement underPriceLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#s0-14-11-6-3-query_answer1-answer-2-1-0-list li:nth-child(1)")));
        if (underPriceLink != null) {
            this.link = underPriceLink;
        }
        String priceString = this.link.getText();
        priceString = StringUtils.replaceChars(priceString, ",", ".");
        this.price = Integer.parseInt(StringUtils.substringBetween(priceString, "$", "."));
    }

    public void click() {
        click(link);
    }

    public int getPrice() {
        return this.price;
    }

}
