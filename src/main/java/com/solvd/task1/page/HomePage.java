package com.solvd.task1.page;

import com.solvd.task1.service.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(css = "#gh-f input[type=text]")
    private WebElement searchInput;
    @FindBy(css = "#gh-f input[type=submit]")
    private WebElement searchButton;
    @FindBy(css = "#gh-eb-u a[href*=sign]")
    private WebElement signInLink;

    public HomePage(RemoteWebDriver driver) {
        super(driver);
        setPageURL(Configuration.getProperty("url"));
    }

    public void writeInSearchLine(String string) {
        sendKeys(this.searchInput, string);
    }

    public void clickSearchButton() {
        click(this.searchButton);
    }

}
