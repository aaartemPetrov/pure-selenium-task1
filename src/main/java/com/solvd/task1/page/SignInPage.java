package com.solvd.task1.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

    private final WebDriver driver;
    @FindBy(css = "#userid")
    private WebElement userInput;
    @FindBy(css = "#signin-continue-btn")
    private WebElement continueButton;

    public SignInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.driver.get(driver.getCurrentUrl());
    }

    public WebElement getUserInput() {
        return this.userInput;
    }

    public void setUserInput(WebElement userInput) {
        this.userInput = userInput;
    }

    public WebElement getContinueButton() {
        return this.continueButton;
    }

    public void setContinueButton(WebElement continueButton) {
        this.continueButton = continueButton;
    }
}
