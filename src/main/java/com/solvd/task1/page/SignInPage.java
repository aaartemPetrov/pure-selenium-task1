package com.solvd.task1.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends AbstractPage {

    @FindBy(css = "#userid")
    private WebElement userInput;
    @FindBy(css = "#signin-continue-btn")
    private WebElement continueButton;

    public SignInPage(WebDriver driver) {
        super(driver);
        setPageURL(getDriver().getCurrentUrl());
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
