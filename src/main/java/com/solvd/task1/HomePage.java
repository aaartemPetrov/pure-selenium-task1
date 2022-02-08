package com.solvd.task1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;
    @FindBy(css = "#gh-f input[type=text]")
    private WebElement searchInput;
    @FindBy(css = "#gh-f input[type=submit]")
    private WebElement searchButton;

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
