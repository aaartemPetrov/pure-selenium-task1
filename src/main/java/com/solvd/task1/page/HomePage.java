package com.solvd.task1.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private final WebDriver driver;
    @FindBy(css = "#gh-f input[type=text]")
    private WebElement searchInput;
    @FindBy(css = "#gh-f input[type=submit]")
    private WebElement searchButton;
    @FindBy(css = "#gh-eb-u a[href*=sign]")
    private WebElement signInLink;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.driver.get("https://www.ebay.com/");
    }

    public void writeInSearchLine(String string) {
        this.searchInput.sendKeys(string);
    }

    public void clickSearchButton() {
        this.searchButton.click();
    }

    public WebElement getSignInLink() {
        return this.signInLink;
    }

    public void setSignInLink(WebElement signInLink) {
        this.signInLink = signInLink;
    }

    public WebElement getSearchInput() {
        return this.searchInput;
    }

    public void setSearchInput(WebElement searchInput) {
        this.searchInput = searchInput;
    }

    public WebElement getSearchButton() {
        return this.searchButton;
    }

    public void setSearchButton(WebElement searchButton) {
        this.searchButton = searchButton;
    }
}
