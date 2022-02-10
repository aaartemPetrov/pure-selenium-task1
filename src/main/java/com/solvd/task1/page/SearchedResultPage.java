package com.solvd.task1.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchedResultPage {

    private WebDriver driver;
    @FindBy(css = ".srp-results *[class=s-item__title]")
    private List<WebElement> searchedItemsTitles;
    @FindBy(css = ".srp-results div.s-item__detail:first-of-type span.s-item__price")
    private List<WebElement> searchedItemsPrices;
    @FindBy(css = ".srp-results a.s-item__link")
    private List<WebElement> searchedItemsLinks;
    @FindBy(css = "#s0-14-11-6-3-query_answer1-answer-2-1-0-list li:nth-child(2)")
    private WebElement fromToPriceLink;


    public SearchedResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.driver.get(driver.getCurrentUrl());
    }

    public List<WebElement> getSearchedItemsTitles() {
        return this.searchedItemsTitles;
    }

    public void setSearchedItemsTitles(List<WebElement> searchedItemsTitles) {
        this.searchedItemsTitles = searchedItemsTitles;
    }

    public List<WebElement> getSearchedItemsPrices() {
        return this.searchedItemsPrices;
    }

    public void setSearchedItemsPrices(List<WebElement> searchedItemsPrices) {
        this.searchedItemsPrices = searchedItemsPrices;
    }

    public WebElement getFromToPriceLink() {
        return this.fromToPriceLink;
    }

    public void setFromToPriceLink(WebElement fromToPriceLink) {
        this.fromToPriceLink = fromToPriceLink;
    }

    public List<WebElement> getSearchedItemsLinks() {
        return this.searchedItemsLinks;
    }

    public void setSearchedItemsLinks(List<WebElement> searchedItemsLinks) {
        this.searchedItemsLinks = searchedItemsLinks;
    }

}
