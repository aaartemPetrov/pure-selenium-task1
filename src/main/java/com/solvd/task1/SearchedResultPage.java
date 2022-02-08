package com.solvd.task1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchedResultPage {

    private WebDriver driver;
    @FindBy(css = ".srp-results *[class=s-item__title]")
    List<WebElement> searchedItems;

    public SearchedResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.driver.get(driver.getCurrentUrl());
    }

    public List<WebElement> getSearchedItems() {
        return searchedItems;
    }

    public void setSearchedItems(List<WebElement> searchedItems) {
        this.searchedItems = searchedItems;
    }
}
